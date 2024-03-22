package com.lego.plugins.basic.exception

/**
 * Thrown when context dependency maven but no version set.
 *
 * @since 1.5
 */
internal class LegoMavenDependencyVersionNotSetException(private val uri :String) : LegoException() {
    override fun toString() =
        "LegoMavenDependencyVersionNotSetException context dependency maven but no version set. uri:[$uri]! "
}