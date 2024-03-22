package com.lego.plugins.basic.exception

/**
 * Thrown when Component in mavenMode but no variant set.
 *
 * @since 1.5
 */
internal class LegoMavenVariantNotSetException(val uri:String) : LegoException() {
    override fun toString() =
        "LegoMavenVariantNotSetException component [${uri}] in mavenMode but no variant set ! "
}