package com.lego.router.exception

import android.net.Uri

/**
 * Thrown when using a bad uri.
 *
 * @since 1.0
 */
internal class BadUriException(private val uri: Uri) : Exception() {
    override fun toString() = "BadUriException on uri:$uri"
}