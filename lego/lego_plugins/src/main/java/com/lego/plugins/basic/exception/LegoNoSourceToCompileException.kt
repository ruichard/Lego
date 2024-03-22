package com.lego.plugins.basic.exception

/**
 * Thrown when compiling kotlin sources, but no .kts.
 *
 * @since 1.6
 */
internal class LegoNoSourceToCompileException(private val absolutePath: String) : LegoException() {
    override fun toString() =
        "LegoNoSourceToCompileException compiling kotlin sources, but no .kts in dir:${absolutePath}! "
}