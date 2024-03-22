package com.lego.route.exception

import com.lego.router.annotations.RInvariant

/**
 * Thrown when using a bad path or version to router..
 *
 * @since 1.0
 */
@RInvariant
class BadPathOrVersionException(val path: String) : Exception() {
    override fun toString() = "BadPathOrVersionException on path:$path"
}