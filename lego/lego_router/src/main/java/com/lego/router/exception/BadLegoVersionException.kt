package com.lego.router.exception

/**
 * Thrown when using a wrong lego version.
 *
 * @since 1.2
 */
internal class BadLegoVersionException(private val msg: String) : Exception() {
    override fun toString() = "BadLegoVersionException using a wrong lego version :$msg"
}