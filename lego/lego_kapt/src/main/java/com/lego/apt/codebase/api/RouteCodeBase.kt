package com.lego.apt.codebase.api

import com.ktnail.x.pathToCamel
import com.ktnail.x.pathToSnake

open class RouteCodeBase(
    val path: String,
    val version: String,
    val navigationOnly: Boolean = false,
    pathSectionOptimize: Boolean = true
) {
    private val pathSection = if (pathSectionOptimize && path.contains("/")) path.split("/") else listOf()
    val sections = if (pathSectionOptimize && pathSection.isNotEmpty()) pathSection.dropLast(1) else listOf()

    val contextFunctionName = (if (pathSectionOptimize && pathSection.isNotEmpty()) pathSection.last() else path).pathToCamel()
    val contextPropertyName = (if (pathSectionOptimize && pathSection.isNotEmpty()) pathSection.last() else path).pathToSnake(true)

    val actionFunctionName = path.pathToCamel()
}


