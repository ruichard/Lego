package com.lego.route.exception

/**
 * Thrown when using a duplicated uri to register context.
 *
 * @since 1.9
 */
internal class DuplicateRegisteredUriException(val uri: String?) : Exception() {
    override fun toString() = "DuplicateRegisteredUriException on uri:$uri"
}