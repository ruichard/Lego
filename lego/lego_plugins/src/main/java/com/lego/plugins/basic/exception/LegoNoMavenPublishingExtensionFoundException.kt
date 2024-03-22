package com.lego.plugins.basic.exception

/**
 * Thrown when your project no maven publishing extension found.
 *
 * @since 1.8
 */
internal class LegoNoMavenPublishingExtensionFoundException : LegoException(){
    override fun toString() =
        "LegoNoMavenPublishingExtensionFoundException make sure already applied 'maven-publish' plugin in build.gradle"
}