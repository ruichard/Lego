package com.lego.apt.codebase.event

import com.blueprint.kotlin.lang.element.KbpElement
import com.blueprint.kotlin.pool.ElementPool
import com.lego.apt.codebase.invoker.InvokeElementCodeBase

class EventInstanceCodeBase(
    val forTag: String,
    val invoker: InvokeElementCodeBase
) {
    companion object {
        operator fun invoke(
            elementPool: ElementPool,
            element: KbpElement,
            forTag: String
        ): EventInstanceCodeBase? {
            return InvokeElementCodeBase(elementPool, element)?.let { invoker ->
                EventInstanceCodeBase(
                    forTag,
                    invoker
                )
            }
        }
    }
}



