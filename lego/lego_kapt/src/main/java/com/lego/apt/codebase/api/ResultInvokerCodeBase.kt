package com.lego.apt.codebase.api

import com.blueprint.kotlin.lang.element.KbpHighOrderFunctionElement
import com.blueprint.kotlin.lang.element.KbpVariableElement
import com.blueprint.kotlin.lang.utility.asTypeElement
import com.blueprint.kotlin.lang.utility.toKbpClassElement
import com.blueprint.kotlin.pool.ElementPool
import com.ktnail.x.findFirstOrNull
import com.ktnail.x.firstOrNull
import com.lego.annotations.route.RResult
import com.lego.apt.CallResultType
import com.lego.apt.Constants
import javax.lang.model.element.ElementKind

data class ResultInvokerCodeBase(
    val name: String,
    val nullable: Boolean,
    val callResultType: CallResultType,
    val parameters: List<ResultInvokerQueryCodeBase>?,
    val className: String
) {
    companion object {
        operator fun invoke(
            element: KbpVariableElement,
            elementPool: ElementPool
        ): ResultInvokerCodeBase? {
            return when (element) {
                is KbpHighOrderFunctionElement -> ResultInvokerCodeBase(
                    element.name,
                    element.type.nullable,
                    CallResultType.HIGHER_ORDER_FUNC,
                    ResultInvokerQueryCodeBase(
                        element.parameters,
                        Constants.Apis.FUNCTION_ARGUMENTS
                    ),
                    element.type.name.toString()
                )
                else -> element.jmElement?.asTypeElement()?.let { typeElement ->
                    typeElement.toKbpClassElement(elementPool)?.functions?.let { functions ->
                        (functions.findFirstOrNull { function ->
                            null != function.jmElement?.getAnnotation(RResult::class.java)
                        } ?: functions.firstOrNull())?.let { function ->
                            ResultInvokerCodeBase(
                                function.name,
                                element.type.nullable,
                                if (typeElement.kind == ElementKind.INTERFACE) CallResultType.INTERFACE else CallResultType.OPEN_CLASS,
                                ResultInvokerQueryCodeBase(function.parameters),
                                element.type.name.toString()
                            )
                        }
                    }
                }
            }
        }
    }

    val callClassCode:String
        get() = if (callResultType == CallResultType.INTERFACE) className else "$className()"

    val queriesHighLevelTypeCode: String
        get() = parameters?.joinToString(",") { "${Any::class.qualifiedName}?" } ?: ""

    fun getQueriesTypeAndNameCode() = parameters?.joinToString(",") { "${it.name}: ${it.toContextTypeName()}" } ?: ""

    val queriesCode: String
        get() = parameters?.joinToString(",") { it.name } ?: ""

    fun queryNamesCode(castAllQuery: Boolean) = parameters?.joinToString(",\n") { codeBase ->
        var code = codeBase.name
        if (codeBase.isRValue) {
            code = Constants.Aggregate.makeToTypeCode(code)
        } else {
            if (castAllQuery) code = Constants.Aggregate.makeCaseToTypeCode(code)
        }
        "  $code"
    }?.let { code ->
        if (code.isNotBlank()) {
            "\n$code\n"
        } else {
            ""
        }
    }

    val queriesRequestsCode: String
        get() = parameters?.joinToString(",") { Constants.Aggregate.makeResultsCode(it.name) } ?: ""

    val needTransform: Boolean
        get() = if (callResultType == CallResultType.HIGHER_ORDER_FUNC) {
            null != parameters?.find { it.isRValue }
        } else {
            true
        }
}