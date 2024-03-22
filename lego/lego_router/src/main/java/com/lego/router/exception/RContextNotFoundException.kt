package com.lego.router.exception

import com.lego.router.annotations.RInvariant

/**
 * Thrown when can not find lego context by uri.
 *
 * @since 1.0
 */
@RInvariant
class RContextNotFoundException(private val uri: String) : Exception(){
    override fun toString() = "RContextNotFoundException on uri:$uri"
}