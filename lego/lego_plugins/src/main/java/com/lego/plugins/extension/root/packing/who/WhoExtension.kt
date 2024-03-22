package com.lego.plugins.extension.root.packing.who

import com.lego.plugins.extension.root.model.All
import com.lego.plugins.extension.root.model.ByTag
import com.lego.plugins.extension.root.model.ByUri
import com.lego.plugins.extension.root.model.PickWho

open class WhoExtension(
    private val onPick: (PickWho) -> Unit
) {
    fun all() {
        onPick(All())
    }

    fun tag(tag: String) {
        onPick(ByTag(tag))
    }

    fun uri(uriOrAuthority: String) {
        onPick(ByUri.create(uriOrAuthority))
    }
}