package com.lego.route.exception

/**
 * Thrown when given name is not a Aggregatable.
 *
 * @since 1.7
 */
internal class BadAggregatableClassException(private val name: String) : Exception() {
    override fun toString() = "BadAggregatableClassException class:$name is not a Aggregatable !"
}