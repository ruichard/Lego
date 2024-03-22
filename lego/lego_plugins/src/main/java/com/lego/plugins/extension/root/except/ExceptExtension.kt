package com.lego.plugins.extension.root.except

import com.lego.plugins.extension.root.model.ByTag
import com.lego.plugins.extension.root.model.ByUri
import com.lego.plugins.extension.root.model.Excepted
import groovy.lang.Closure
import org.gradle.util.ConfigureUtil

open class ExceptExtension(
    private val forFlavor: String?,
    private val onExcept: (Excepted) -> Unit
) {

    fun flavor(name: String, closure: Closure<ExceptExtension>) {
        ExceptExtension(name, onExcept).apply {
            ConfigureUtil.configure(closure, this)
        }
    }

    fun tag(tag: String) {
        onExcept(Excepted(forFlavor, ByTag(tag)))
    }

    fun uri(uriOrAuthority: String) {
        onExcept(Excepted(forFlavor, ByUri.create(uriOrAuthority)))
    }
}