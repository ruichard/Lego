package com.lego.plugins.extension.root.packing.who

import com.lego.plugins.extension.root.packing.NoSourceExtension
import com.lego.plugins.extension.root.model.All
import com.lego.plugins.extension.root.model.ByTag
import com.lego.plugins.extension.root.model.ByUri
import com.lego.plugins.extension.root.model.PickWho
import groovy.lang.Closure
import org.gradle.util.ConfigureUtil

open class WhoNoSourceExtension(
    private val onPick: (PickWho, NoSourceExtension?) -> Unit
) {
    fun all() {
        onPick(All(), null)
    }

    fun tag(tag: String) {
        onPick(ByTag(tag), null)
    }

    fun uri(uriOrAuthority: String) {
        onPick(ByUri.create(uriOrAuthority), null)
    }

    fun all(closure: Closure<NoSourceExtension>) {
        onPick(All(), NoSourceExtension().apply {
            ConfigureUtil.configure(closure, this)
        })
    }

    fun tag(tag: String, closure: Closure<NoSourceExtension>) {
        onPick(ByTag(tag), NoSourceExtension().apply {
            ConfigureUtil.configure(closure, this)
        })
    }

    fun uri(uriOrAuthority: String, closure: Closure<NoSourceExtension>) {
        onPick(ByUri.create(uriOrAuthority), NoSourceExtension().apply {
            ConfigureUtil.configure(closure, this)
        })
    }
}