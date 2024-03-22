package com.lego.plugins.basic.exception

/**
 * Thrown when component defined multiple times.
 *
 * @since 1.8
 */
internal open class LegoMultipleComponentDefinedException(private val uri:String) : LegoException() {
    override fun toString() =
        "LegoMultipleComponentDefinedException Component[$uri] defined multiple times in gradle files! "
}
