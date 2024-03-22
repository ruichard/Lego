package com.lego.plugins.root.pick

import com.android.build.gradle.api.BaseVariant
import com.ktnail.x.Logger
import com.lego.plugins.basic.LogTags
import com.lego.plugins.basic.exception.LegoException
import com.lego.plugins.basic.utility.p
import com.lego.plugins.basic.utility.legoExtension
import com.lego.plugins.basic.utility.toUriIfAuthority
import com.lego.plugins.extension.context.ContextExtension
import com.lego.plugins.extension.context.provider.ContextsProvider
import com.lego.plugins.extension.context.source.SourceExtension
import com.lego.plugins.extension.root.model.*
import org.gradle.api.Project

class ContextPicker(private val rootProject:Project){

    private val consumedPickedContexts = mutableMapOf<String, PickedContext>()
    private val consumedFlavorPickedContexts = mutableMapOf<String, MutableMap<String, PickedContext>>()

    fun pickedContexts(variant: BaseVariant) =
        mutableMapOf<String, PickedContext>().apply {
            this += consumedPickedContexts
            variant.productFlavors.mapNotNull { flavor -> flavor.name }.forEach { flavor ->
                consumedFlavorPickedContexts[flavor]?.let { pickedContexts ->
                    this += pickedContexts
                }
            }
    }

    fun pick(action: (Picked, ContextExtension, SourceExtension?) -> Unit) {
        ContextsProvider(rootProject).let { provider ->
            rootProject.legoExtension.pickedContextsSoFar.forEach { picked ->
                when (picked.who){
                    is ByFuzzyUri -> {
                        provider.observeAnyRegister { extension ->
                            if (picked.who.matching(extension.uri)) {
                                consumePickedContext(picked, extension) { action(picked, extension, null) }
                            }
                        }
                    }
                    is ByUri -> {
                        provider.observeUriRegister(picked.who.uriOrAuthority.toUriIfAuthority(rootProject)) { extension ->
                            consumePickedContext(picked, extension) { action(picked, extension, null) }
                        }
                    }
                    is ByTag -> {
                        provider.observeAnyRegister { extension ->
                            if (extension.tags.containsKey(picked.who.tag)) {
                                consumePickedContext(picked, extension) { action(picked, extension, extension.tags[picked.who.tag]) }
                            }
                        }
                    }
                    is All -> {
                        provider.observeAnyRegister { extension ->
                            consumePickedContext(picked, extension) { action(picked, extension, null) }
                        }
                    }
                }
            }
        }
    }

    private fun consumePickedContext(
        picked: Picked,
        context: ContextExtension,
        action: () -> Unit
    ) {
        try {
            if (null != picked.forFlavor) {
                consumedFlavorPickedContexts.getOrPut(picked.forFlavor) { mutableMapOf() }.let { pickedContexts ->
                    if (!pickedContexts.containsKey(context.uri)) {
                        action()
                        pickedContexts[context.uri] = PickedContext(picked, context).apply {
                            Logger.p(LogTags.PICK_CONTEXT, null) { " $this" }
                        }
                    }
                }
            } else {
                if (!consumedPickedContexts.containsKey(context.uri)) {
                    action()
                    consumedPickedContexts[context.uri] = PickedContext(picked, context).apply {
                        Logger.p(LogTags.PICK_CONTEXT, null) { " $this" }
                    }
                }
            }
        } catch (e: LegoException) {
            e.printStackTrace()
        }
    }
}

data class PickedContext(val picked: Picked, val extension: ContextExtension){
    override fun toString(): String {
        return "${extension.uri} BY: {$picked}"
    }
}