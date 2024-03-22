package com.lego.plugins.basic.publish.maven

import com.lego.plugins.basic.exception.LegoMavenPublishingTaskNotFoundException
import com.lego.plugins.basic.exception.LegoMavenRepositoryNotSetException
import com.lego.plugins.basic.exception.LegoNoMavenPublishingExtensionFoundException
import com.lego.plugins.basic.utility.*
import groovy.util.Node
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.artifacts.Dependency
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication

open class PublishingTaskProvider(val project: Project) {
    companion object {
        const val LEGO_REMOTE_REPOSITORY_NAME = "LegoRemoteMaven"
        const val LEGO_LOCAL_REPOSITORY_NAME = "LegoLocalMaven"
    }

    private var _extension: PublishingExtension? = null
    protected val extension: PublishingExtension
        get() {
            if (null == _extension){
                _extension = (project.extensions.getByName("publishing") as? PublishingExtension)
                setRepositories()
            }
            return _extension ?: throw LegoNoMavenPublishingExtensionFoundException();
        }

    fun getTask(pub: Publication, dev: Boolean = false): Task {
        val publicationName = if (dev) pub.devPublicationName else pub.publicationName
        extension.publications { publications ->
            publications.register(
                publicationName,
                MavenPublication::class.java
            ) { publication ->
                publication.artifactId = pub.artifactId
                publication.groupId = pub.groupId
                publication.version = if (dev) pub.devVersion else pub.version
                publication.artifact(pub.publicationFile)
                pub.source?.let { source ->
                    publication.artifact(source.publicationFile) {
                        it.classifier = "sources"
                    }
                }
                if (pub.addDependencies.isNotEmpty() || pub.addDependencyTypes.isNotEmpty()) {
                    publication.pom { pom ->
                        pom.withXml { xmlProvider ->
                            xmlProvider.asNode().appendNode("dependencies").apply {
                                appendDependencyPom(pub.addDependencies, dev)
                                appendDependencyPom(project, pub.addDependencyTypes)
                            }
                        }
                    }
                }
            }
        }
        return project.tasks.getByName(
            if (dev) getPublishingLocalTaskName(publicationName)
            else getPublishingTaskName(publicationName)
        ) ?: throw LegoMavenPublishingTaskNotFoundException(
            publicationName
        )
    }

    private fun setRepositories() {
        extension.apply {
            repositories { repositories ->
                repositories.maven { maven ->
                    maven.name = LEGO_REMOTE_REPOSITORY_NAME
                    maven.url = project.legoMavenRepository
                    maven.credentials { credentials ->
                        credentials.username = project.propertyOr(Ext.LEGO_MAVEN_USERNAME) { throw LegoMavenRepositoryNotSetException() }
                        credentials.password = project.propertyOr(Ext.LEGO_MAVEN_PASSWORD) { throw LegoMavenRepositoryNotSetException() }
                    }
                }
                repositories.maven { maven ->
                    maven.name = LEGO_LOCAL_REPOSITORY_NAME
                    maven.url = project.legoMavenLocalRepository
                }
            }
        }
    }

    private fun Node.appendDependencyPom(
        addDependencies: Array<Publication>,
        dev: Boolean
    ) {
        addDependencies.forEach { pub ->
            appendNode("dependency").apply {
                appendNode("groupId", pub.groupId)
                appendNode("artifactId", pub.artifactId)
                appendNode("version",  if (dev) pub.devVersion else pub.version)
            }
        }
    }

    private fun Node.appendDependencyPom(
        project: Project,
        addDependencyTypes: Array<String>
    ) {
        addDependencyTypes.forEach { type ->
            project.getDependencySet(type)?.forEach { dependency ->
                this.appendDependencyPom(dependency)
            }
        }
    }

    private fun Node.appendDependencyPom(dependency: Dependency) {
        if (dependency.legal()) {
            appendNode("dependency").apply {
                appendNode("groupId", dependency.group)
                appendNode("artifactId", dependency.name)
                appendNode("version", dependency.version)
            }
        }
    }

    private fun Dependency.legal() = group.legalDependency() && name.legalDependency() && version.legalDependency()

    private fun String?.legalDependency() = this?.isNotBlank() == true && "unspecified" != this

    protected fun getPublishingTaskName(publicationName: String) = "publish${publicationName}PublicationTo${LEGO_REMOTE_REPOSITORY_NAME}Repository"

    protected fun getPublishingLocalTaskName(publicationName: String) = "publish${publicationName}PublicationTo${LEGO_LOCAL_REPOSITORY_NAME}Repository"
}