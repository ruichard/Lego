package com.lego.plugins.basic.exception

/**
 * Thrown when lego-text project is not a lego-root project.
 *
 * @since 1.3
 */
internal class LegoTestNotLegoRootException(private val project:String?) : LegoException(){
    override fun toString() =
        "LegoTestNotLegoRootException if you want test, your project must be a lego-root first! project[$project]"
}