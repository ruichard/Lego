package com.lego.plugins.basic.exception

/**
 * Thrown when maven publishing task was not found for given publication.
 *
 * @since 1.5
 */
internal class LegoMavenPublishingTaskNotFoundException(private val publicationName:String) : LegoException() {
    override fun toString() =
        "LegoMavenPublishingTaskNotFoundException maven publishing task was not found for given publication[${publicationName}], make sure publishing plugin right , please! "
}