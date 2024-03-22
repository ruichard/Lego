package com.lego.apt.codebase.api

import com.blueprint.kotlin.lang.element.KbpVariableElement
import com.blueprint.kotlin.lang.type.KbpType

class ResultInvokerQueryCodeBase(
    val name: String,
    type: KbpType
) : TypeCodeBase(type) {
    companion object {
        operator fun invoke(
            parameters: List<KbpType>, prefix: String
        ): List<ResultInvokerQueryCodeBase> =
            parameters.mapIndexed { index, type ->
                ResultInvokerQueryCodeBase(
                    "$prefix$index",
                    type
                )
            }

        operator fun invoke(
            parameters: List<KbpVariableElement>
        ): List<ResultInvokerQueryCodeBase> =
            parameters.map { variableElement ->
                ResultInvokerQueryCodeBase(
                    variableElement.name,
                    variableElement.type
                )
            }

    }
}