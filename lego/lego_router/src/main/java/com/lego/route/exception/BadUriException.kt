package com.lego.route.exception

/**
 * Thrown when using a bad path or version to router..
 *
 * @since 1.8
 */
internal class BadUriException(val uri: String?) : Exception() {
    override fun toString() = "BadUriException on uri:$uri"
}