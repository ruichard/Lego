package com.lego.router

import com.lego.Lego
import com.lego.router.annotations.RInvariant

/**
 * A Holder for lego context to touch other.
 *
 * @since 1.6
 */
@RInvariant
open class TouchHolder(private val uri: String) {
    private var touched: Boolean? = null

    private fun tryTouch() {
        if (null == touched)
            touched = Lego.touchUri(uri)
    }

    /**
     * Do touch other context action.
     *
     * @param action invoke if can touch this context.
     */
    @RInvariant
    fun touch(action: () -> Unit) = apply {
        tryTouch()
        if (touched == true) {
            action()
        }
    }

    /**
     * Do touch other context action.
     *
     * @param action invoke if can not touch this context.
     */
    @RInvariant
    fun miss(action: () -> Unit) = apply {
        tryTouch()
        if (touched == false) {
            action()
        }
    }
}