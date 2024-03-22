package com.lego.route.exception

/**
 * Thrown when using a bad result to router.
 *
 * @since 1.0
 */
internal class BadResultException(private var index: Int) : Exception() {
    override fun toString() = "BadResultException on index:$index"
}