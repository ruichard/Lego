package com.lego.plugins.basic.exception

/**
 * Thrown when making a jar with no classes.
 *
 * @since 1.4
 */
internal class LegoNoClassesToJarException(private val absolutePath: String) : LegoException() {
    override fun toString() =
        "LegoNoClassesToJarException making a jar with no classes in dir:${absolutePath}! "
}