package com.lego.plugins.basic.exception

/**
 * Thrown when context dependency maven but no version set.
 *
 * @since 1.5
 */
internal class LegoRouterDependencyNotSetException : LegoException() {
    override fun toString() =
        "LegoRouterDependencyNotSetException  please make sure ext.lego_router_version in build.gradle of root project !"
}