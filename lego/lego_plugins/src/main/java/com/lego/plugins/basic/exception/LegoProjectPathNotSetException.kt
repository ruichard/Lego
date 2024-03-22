package com.lego.plugins.basic.exception

/**
 * Thrown when Context in projectMode but no project path set.
 *
 * @since 1.5
 */
internal class LegoProjectPathNotSetException(private val uri:String) : LegoException() {
    override fun toString() =
        "LegoProjectPathNotSetException uri [${uri}] in projectMode but no project path set ! "
}