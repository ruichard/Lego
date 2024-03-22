package com.lego.router.exception

/**
 * Thrown when lego router was not initialized.
 *
 * @since 1.0
 */
internal class LegoNotInitException : Exception(){
    override fun toString() = "LegoNotInitException please call Lego.init() first!"
}