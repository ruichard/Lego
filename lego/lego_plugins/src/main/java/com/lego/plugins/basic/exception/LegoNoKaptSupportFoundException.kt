package com.lego.plugins.basic.exception

/**
 * Thrown when your project no kapt support.
 *
 * @since 1.3
 */
internal class LegoNoKaptSupportFoundException(private val project:String?) : LegoException(){
    override fun toString() =
        "LegoNoKaptSupportFoundException your project must add kapt support ! project[$project]"
}