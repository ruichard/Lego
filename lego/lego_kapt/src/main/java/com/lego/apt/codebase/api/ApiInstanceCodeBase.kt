package com.lego.apt.codebase.api

import com.blueprint.kotlin.lang.element.KbpElement
import com.blueprint.kotlin.pool.ElementPool
import com.lego.apt.codebase.invoker.InvokeElementCodeBase

class ApiInstanceCodeBase(
    val forPath: String,
    val version: String,
    val invoker: InvokeElementCodeBase
) {
    companion object {
        operator fun invoke(
            elementPool: ElementPool,
            element: KbpElement,
            forPath: String,
            version: String
        ): ApiInstanceCodeBase? {
            return InvokeElementCodeBase(elementPool, element)?.let { invoker ->
                ApiInstanceCodeBase(
                    forPath,
                    version,
                    invoker
                )
            }
        }
    }
}



