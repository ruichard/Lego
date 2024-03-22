package com.lego.apt.codebase.api

import com.blueprint.kotlin.lang.element.KbpVariableElement
import com.blueprint.kotlin.lang.type.KbpType
import com.blueprint.kotlin.pool.ElementPool
import com.ktnail.x.camelToPascal
import com.lego.annotations.route.RExtend
import com.lego.apt.Constants
import com.lego.apt.utility.isResultInvoker
import com.lego.apt.utility.isVarargs

/**
 * The code structure of Router Query.
 *
 * @since 1.1
 */
class QueryCodeBase(
    private var name: String,
    type: KbpType,
    val isExtendThis: Boolean,
    val isVarargs: Boolean,
    val resultInvoker: ResultInvokerCodeBase?
) : TypeCodeBase(type) {
    companion object {
        operator fun invoke(
            parameters: List<KbpType>, prefix: String
        ): List<QueryCodeBase> =
            parameters.mapIndexed { index, type ->
                QueryCodeBase(
                    "$prefix$index",
                    type,
                    isExtendThis = false,
                    isVarargs = false,
                    resultInvoker = null
                )
            }

        operator fun invoke(
            parameters: List<KbpVariableElement>,
            elementPool: ElementPool
        ): List<QueryCodeBase> =
            parameters.map { variableElement ->
                QueryCodeBase(
                    variableElement.name,
                    variableElement.type,
                    isExtendThis = null != variableElement.jmElement?.getAnnotation(RExtend::class.java),
                    isVarargs = variableElement.type.isVarargs(),
                    resultInvoker = if (variableElement.isResultInvoker()) ResultInvokerCodeBase(
                        variableElement,
                        elementPool
                    ) else null
                )
            }
    }

    fun addNameAssistPrefix() {
        if (!name.startsWith(Constants.Apis.PARAMETER_NAME_INSTANCE_PREFIX)) {
            name = "${Constants.Apis.PARAMETER_NAME_INSTANCE_PREFIX}${name.camelToPascal()}"
        }
    }

    val legalName
        get() = Constants.Apis.toLegalParameterName(name)

    val originalName
        get() = name

    val callName
        get() = if (isVarargs) "*${legalName}" else legalName

    fun makeGetQueryContextTypeCode(name: String, index: Int) =
        Constants.Aggregate.makeGetQueryCode(name, index)

}