package com.lego.route.exception

/**
 * Thrown when using a bad query to router.
 *
 * @since 1.0
 */
internal class BadQueryException(private val index: Int, private val name: String?) : Exception() {
    override fun toString() = "BadQueryException on index:$index name:$name"
}