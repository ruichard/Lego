package com.lego.plugins.basic.exception

/**
 * Thrown when can not find kotlin compile task.
 *
 * @since 1.6
 */
internal class LegoNoKotlinCompileFoundException(private val variant:String?) : LegoException(){
    override fun toString() =
        "LegoNoKaptTaskFoundException can not find kotlin compile task for variant[$variant]，make sure your project has kotlin support!"
}