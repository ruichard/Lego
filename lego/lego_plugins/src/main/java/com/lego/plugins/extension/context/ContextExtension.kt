package com.lego.plugins.extension.context

import com.ktnail.x.uri.buildUri
import com.ktnail.x.uri.toSchemeAndAuthority
import com.lego.plugins.basic.exception.LegoComponentUriException
import com.lego.plugins.context.declare.ContextDeclare
import com.lego.plugins.context.model.Component.Companion.nameToComponentArtifactId
import com.lego.plugins.context.model.Lib.Companion.nameToLibArtifactId
import com.lego.plugins.context.model.Lib.Companion.versionToDevVersion
import com.lego.plugins.extension.LegoExtension
import com.lego.plugins.extension.context.dependency.DependenciesExtension
import com.lego.plugins.extension.context.dependency.DependencyExtension
import com.lego.plugins.extension.context.source.SourceExtension
import groovy.lang.Closure
import org.gradle.util.ConfigureUtil

/**
 *  Lego context extension of gradle plugins.
 *
 *  @since 1.3
 */
open class ContextExtension(val lego: LegoExtension, private val keyWord: String = "context") {

    private var _scheme: String? = null

    private var _authority: String? = null

    private var _name: String = ""
    private var _group: String = ""
    private var _uri: String? = null

    private val _source = SourceExtension()
    private val _dependencies = DependenciesExtension(lego)

    private val scheme: String
        get() = _scheme ?: lego.globalScheme

    val uri
        get() = _uri ?: buildUri(
            scheme, _authority ?: throw LegoComponentUriException("$_uri|$_authority", keyWord)
        )

    var enableProvideRoute = true

    var enablePublishComponent = true
        get() = field && (enablePublish || enablePublishDev)

    fun uri(uri: String) {
        if (null == _uri) {
            _uri = uri
        } else {
            throw LegoComponentUriException(
                "$_uri|$_authority",
                keyWord
            )
        }
        uri.toSchemeAndAuthority().apply {
            scheme(this.first)
            authority(this.second)
        }
    }

    fun scheme(scheme: String) {
        if (_scheme == null) {
            _scheme = scheme
        } else {
            throw LegoComponentUriException(
                "$_uri|$_authority",
                keyWord
            )
        }
    }

    fun authority(authority: String) {
        if (_authority == null) {
            _name = authority.substringAfterLast(".")
            _group = authority.substringBeforeLast(".")
            _authority = authority
        } else {
            throw LegoComponentUriException(
                "$_uri|$_authority",
                keyWord
            )
        }
    }

    fun dependencies(closure: Closure<DependenciesExtension>) {
        ConfigureUtil.configure(closure, _dependencies)
    }

    val dependencyExtensions
        get() = _dependencies.dependencies

    // source
    fun source(closure: Closure<SourceExtension>) {
        ConfigureUtil.configure(closure, _source)
    }

    val projectPath
        get() = _source.project.path

    val mavenVersion
        get() = lego.globalConfig.getMavenVersion(uri, _source.maven.version)

    val mavenVariant
        get() = _source.maven.variant ?: lego.globalConfig.mavenVariant

    // publish
    fun updateProjectPathIfNull(project: String) {
        _source.updateProjectPathIfNull(project)
    }

    val enablePublish
        get() = null != versionToPublish

    val enablePublishDev
        get() = null != versionToPublishDev

    val projectPathToPublish
        get() = _source.project.path

    val versionToPublish
        get() = _source.project.publishVersion ?: lego.globalConfig.getMavenPublishVersion(uri)

    val versionToPublishDev
        get() = versionToPublish?.versionToDevVersion()

    val publishArtifactName
        get() = _name

    val publishGroupId
        get() = _group

    val publishOriginalValue
        get() = _source.project.publishOriginalValue

    fun publishLibArtifactId(libType: String) = _name.nameToLibArtifactId(libType)

    fun publishComponentArtifactId(variantName: String) = _name.nameToComponentArtifactId(variantName)

    // tag
    val tags = mutableMapOf<String, SourceExtension?>()

    fun tag(name: String, closure: Closure<SourceExtension>) {
        SourceExtension().apply {
            ConfigureUtil.configure(closure, this)
            tags[name] = this
        }
    }

    fun tag(name: String) {
        tags[name] = null
    }

    // declare
    fun toPublishContextDeclare() =
        ContextDeclare(
            uri,
            listOf(),
            _name,
            versionToPublish ?: "unkown"
        )


    override fun toString() =
        "ContextExtension : scheme:$scheme authority:$_authority dependencies:$_dependencies source:$_source name:$_name group:$_group"

    fun toDependencyExtension(): DependencyExtension{
        val contextUri = this.uri
        return DependencyExtension(lego).apply {
            setUri(contextUri)
        }
    }

}