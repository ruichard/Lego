package com.lego.plugins.basic.exception

/**
 * Thrown when not lego-config apply.
 *
 * @since 1.5
 */
internal class LegoConfigNotFoundException : LegoException(){
    override fun toString() =
        "LegoConfigNotFoundException please apply lego-config plugin in build.gradle of root project first!"
}