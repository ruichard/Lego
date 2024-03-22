package com.lego.plugins.basic.exception

/**
 * Thrown when maven maven repository was not set.
 *
 * @since 1.5
 */
internal class LegoMavenRepositoryNotSetException : LegoException() {
    override fun toString() =
        "LegoMavenRepositoryNotSetException  please make sure ext.lego_maven_repository、ext.lego_maven_username and ext.lego_maven_password in build.gradle of root project !"
}