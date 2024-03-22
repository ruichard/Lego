package com.lego.plugins.extension

import com.lego.plugins.basic.exception.LegoDefaultSchemeNotSetException
import com.lego.plugins.extension.context.ContextExtension
import com.lego.plugins.extension.global.GlobalExtension
import com.lego.plugins.extension.root.except.ExceptExtension
import com.lego.plugins.extension.root.model.Picked
import com.lego.plugins.extension.root.packing.PackingExtension
import com.lego.plugins.extension.root.packing.mode.PickExtension
import com.lego.plugins.basic.utility.legoConfigExtension
import groovy.lang.Closure
import org.gradle.api.Project
import org.gradle.util.ConfigureUtil
import java.io.File

/**
 *  Lego extension of gradle plugins.
 *
 *  @since 1.3
 */
open class LegoExtension(val project: Project) {
    // config
    private val configExtension
        get() = project.legoConfigExtension

    // global
    val globalConfig: GlobalExtension
        get() = configExtension.globalConfig

    val globalScheme: String
        get() = configExtension.globalConfig.scheme ?: throw  LegoDefaultSchemeNotSetException()

    fun global(closure: Closure<GlobalExtension>) {
        ConfigureUtil.configure(closure, configExtension.globalConfig)
    }

    // aggregate set
    private val aggregateSetChangedListeners = mutableListOf<(String) -> Unit>()

    open var aggregateSet: String = "${project.projectDir}${File.separator}src${File.separator}main${File.separator}aggregate"
        set(value) {
            field = value
            aggregateSetChangedListeners.forEach { action ->
                action(value)
            }
        }

    fun listenAggregateSetChanged(action: (String) -> Unit) {
        action(aggregateSet)
        aggregateSetChangedListeners.add(action)
    }

    // contexts
    fun router(closure: Closure<ContextExtension>) {
        ContextExtension(this).apply {
            ConfigureUtil.configure(closure, this)
            enableProvideRoute = true
            enablePublishComponent = false
            configExtension.register(this)
        }
    }

    fun library(closure: Closure<ContextExtension>) {
        ContextExtension(this).apply {
            ConfigureUtil.configure(closure, this)
            enableProvideRoute = false
            enablePublishComponent = true
            configExtension.register(this)
        }
    }

    fun context(closure: Closure<ContextExtension>) {
        ContextExtension(this).apply {
            ConfigureUtil.configure(closure, this)
            enableProvideRoute = true
            enablePublishComponent = true
            configExtension.register(this)
        }
    }

    fun component(closure: Closure<ContextExtension>) {
        ContextExtension(this, "component").apply {
            ConfigureUtil.configure(closure, this)
            enableProvideRoute = true
            enablePublishComponent = true
            configExtension.register(this)
        }
    }

    // rootContexts
    private val _pickedContexts: MutableList<Picked> = mutableListOf()

    val pickedContextsSoFar: List<Picked>
        get() = _pickedContexts.reversed()

    fun pick(closure: Closure<PickExtension>) {
        PickExtension(null) { picked ->
            _pickedContexts.add(picked)
        }.apply {
            ConfigureUtil.configure(closure, this)
        }
    }

    fun packing(closure: Closure<PackingExtension>) {
        PackingExtension(null) { picked ->
            _pickedContexts.add(picked)
        }.apply {
            ConfigureUtil.configure(closure, this)
        }
    }

    fun except(closure: Closure<ExceptExtension>) {
        ExceptExtension(null) { excepted ->
            _pickedContexts.removeIf { picked ->
                picked.forFlavor == excepted.forFlavor && (picked.who == excepted.who)
            }
        }.apply {
            ConfigureUtil.configure(closure, this)
        }
    }

}