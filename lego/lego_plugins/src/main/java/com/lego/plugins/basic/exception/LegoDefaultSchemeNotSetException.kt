package com.lego.plugins.basic.exception

/**
 * Thrown when use only authority to build uri, but no defaultScheme set.
 *
 * @since 1.6
 */
internal class LegoDefaultSchemeNotSetException :LegoException() {
    override fun toString() =
        "LegoDefaultSchemeNotSetException if you want build uri use only authority , please set defaultScheme in context first! "
}