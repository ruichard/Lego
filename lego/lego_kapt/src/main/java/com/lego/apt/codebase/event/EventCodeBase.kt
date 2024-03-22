package com.lego.apt.codebase.event

import com.blueprint.kotlin.lang.element.KbpElement
import com.blueprint.kotlin.pool.ElementPool
import com.lego.apt.codebase.invoker.InvokeElementCodeBase

/**
 * The code structure of Router Event.
 *
 * @since 1.1
 */
class EventCodeBase(
    val msg: String,
    val tag: String?,
    val invoker: InvokeElementCodeBase
) {
    companion object {
        operator fun invoke(
            elementPool: ElementPool,
            element: KbpElement,
            msg: String,
            tag: String?
        ): EventCodeBase? {
            return InvokeElementCodeBase(elementPool, element)?.let { invoker ->
                EventCodeBase(
                    msg,
                    tag,
                    invoker
                )
            }
        }
    }
}



