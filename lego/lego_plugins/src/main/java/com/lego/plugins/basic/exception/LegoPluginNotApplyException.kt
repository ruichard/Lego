package com.lego.plugins.basic.exception

/**
 * Thrown when plugin not apply yet.
 *
 * @since 1.3
 */
internal class LegoPluginNotApplyException : LegoException() {
    override fun toString() = "LegoPluginNotApplyException plugin not apply yet!"
}