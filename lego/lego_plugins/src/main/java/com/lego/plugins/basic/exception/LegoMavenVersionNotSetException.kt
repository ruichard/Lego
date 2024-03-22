package com.lego.plugins.basic.exception

/**
 * Thrown when Component in mavenMode but no version set.
 *
 * @since 1.5
 */
internal open class LegoMavenVersionNotSetException(val uri:String) : LegoException() {
    override fun toString() =
        "LegoMavenVersionNotSetException component[${uri}] in mavenMode but no version set ! "
}

/**
 * Thrown when Context in test mode but no version set.
 *
 * @since 1.7
 */
internal class LegoTestMavenVersionNotSetException( uri:String) : LegoMavenVersionNotSetException(uri) {
    override fun toString() =
        "LegoTestMavenVersionNotSetException component[${uri}] in test but no version set ! "
}