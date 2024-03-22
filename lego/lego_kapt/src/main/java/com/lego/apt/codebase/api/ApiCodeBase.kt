package com.lego.apt.codebase.api

import com.blueprint.kotlin.lang.element.KbpElement
import com.blueprint.kotlin.pool.ElementPool
import com.lego.apt.codebase.invoker.InvokeContextCodeBase
import com.lego.apt.codebase.invoker.InvokeElementCodeBase
import com.squareup.kotlinpoet.TypeName

/**
 * The code structure of Router Api.
 *
 * @since 1.1
 */
class ApiCodeBase(
    path: String,
    version: String,
    navigationOnly: Boolean,
    pathSectionOptimize: Boolean,
    val originalInvoker: InvokeElementCodeBase,
    val defineResultType: String?,
    val forResult: Boolean = false
) : RouteCodeBase(path, version, navigationOnly, pathSectionOptimize) {
    companion object {
        operator fun invoke(
            elementPool: ElementPool,
            element: KbpElement,
            path: String,
            version: String,
            defineResultType: String? = null,
            navigationOnly: Boolean,
            forResult: Boolean,
            pathSectionOptimize: Boolean
        ): ApiCodeBase? {
            return InvokeElementCodeBase(elementPool, element)?.let { invoker ->
                ApiCodeBase(
                    path,
                    version,
                    navigationOnly,
                    pathSectionOptimize,
                    invoker,
                    defineResultType,
                    forResult
                )
            }
        }
    }

    fun contextInvoker(uri: String) = InvokeContextCodeBase(this, uri)
}



