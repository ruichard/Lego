package com.lego.plugins.basic.exception

/**
 * Thrown when can not find lego-config plugin .
 *
 * @since 1.8
 */
internal class LegoConfigPluginNotApplyException : LegoException() {
    override fun toString() = "LegoConfigPluginNotApplyException did you applied 'lego-config' plugin in your gradle root project!"
}