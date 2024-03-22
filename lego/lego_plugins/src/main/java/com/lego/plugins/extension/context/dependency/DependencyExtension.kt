package com.lego.plugins.extension.context.dependency

import com.ktnail.x.uri.buildUri
import com.lego.plugins.context.model.Lib.Companion.nameToLibArtifactId
import com.lego.plugins.context.declare.ContextDeclare
import com.lego.plugins.basic.exception.LegoDefaultSchemeNotSetException
import com.lego.plugins.basic.exception.LegoMavenDependencyVersionNotSetException
import com.lego.plugins.extension.LegoExtension

open class DependencyExtension(val lego: LegoExtension) {
    private var _uri: String? = null
    private var _version: String? = null
    private var _supportOriginalValue = false

    private var _authority: String = ""
    private var _name: String = ""
    private var _group: String = ""

    fun setUri(uri: String) {
        _authority = uri.substringAfterLast("://")
        _name = _authority.substringAfterLast(".")
        _group = _authority.substringBeforeLast(".")
        _uri = uri
    }

    fun setAuthority(authority: String) {
        _authority = authority
        _name = _authority.substringAfterLast(".")
        _group = _authority.substringBeforeLast(".")
        _uri = buildUri(
            lego.globalConfig.scheme ?: throw  LegoDefaultSchemeNotSetException(),
            authority
        )
    }

    fun version(version: String) {
        _version = version
    }

    fun supportOriginalValue(support: Boolean) {
        _supportOriginalValue = support
    }

    val groupId
        get() = _group

    fun versionToDependency(dev: Boolean?): String {
        return (if (null != dev) lego.globalConfig.getMavenVersion(uri, _version, dev = dev)
        else lego.globalConfig.getMavenVersion(uri, _version)) ?: throw LegoMavenDependencyVersionNotSetException(uri)
    }

    val uri
        get() = _uri.toString()

    val supportOriginalValue
        get() = _supportOriginalValue

    fun artifactId(libTYpe: String) = _name.nameToLibArtifactId(libTYpe)

    fun toUriDeclare() = _uri?.let { uri -> ContextDeclare(uri, null, "", versionToDependency(false)) }

    override fun toString() = "DependencyExtension: uri:$_uri version:${versionToDependency(false)} supportOriginalValue:$_supportOriginalValue"
}