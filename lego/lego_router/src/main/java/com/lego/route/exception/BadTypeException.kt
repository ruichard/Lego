package com.lego.route.exception

import com.lego.router.annotations.RInvariant

/**
 * Thrown when casing value type type error when router.
 *
 * @since 1.0
 */
@RInvariant
class BadTypeException(private val type: String= "unknow") : Exception() {
    override fun toString() = "BadTypeException on type:$type"
}