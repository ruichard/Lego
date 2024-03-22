package com.lego.plugins.basic.exception

/**
 * Thrown when your project no kapt task found.
 *
 * @since 1.6
 */
internal class LegoNoKaptTaskFoundException(private val variant:String?) : LegoException(){
    override fun toString() =
        "LegoNoKaptTaskFoundException can not find kapt task for variant[$variant]"
}