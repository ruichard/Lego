package com.lego.route.exception

import com.lego.router.annotations.RInvariant

/**
 * Thrown when value not expect type when router.
 *
 * @since 1.0
 */
@RInvariant
class BadValueException(private val toType: String ,private val from: Any?) : Exception() {
    override fun toString() = "BadValueException expect:$toType but:${if (null == from) "null" else from::class.java.name}"
}