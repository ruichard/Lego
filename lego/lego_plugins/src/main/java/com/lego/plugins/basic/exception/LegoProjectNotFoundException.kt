package com.lego.plugins.basic.exception

/**
 * Thrown when Context in projectMode but no project path set.
 *
 * @since 1.8
 */
internal class LegoProjectNotFoundException(private val uri:String, private val path:String) : LegoException() {
    override fun toString() =
        "LegoProjectPathNotFoundException uri[${uri}] in projectMode but not found project use path[$path] ! "
}