package com.lego.plugins.extension.context.dependency

import com.lego.plugins.extension.LegoExtension
import groovy.lang.Closure
import org.gradle.util.ConfigureUtil

open class DependenciesExtension(val lego: LegoExtension) {
    val dependencies: MutableList<DependencyExtension> = mutableListOf()

    fun uri(uri: String) {
        DependencyExtension(lego).apply {
            this.setUri(uri)
            dependencies.add(this)
        }
    }

    fun authority(authority: String) {
        DependencyExtension(lego).apply {
            this.setAuthority(authority)
            dependencies.add(this)
        }
    }

    fun uri(uri: String, closure: Closure<DependencyExtension>) {
        DependencyExtension(lego).apply {
            this.setUri(uri)
            dependencies.add(this)
            ConfigureUtil.configure(closure, this)
        }
    }

    fun authority(authority: String, closure: Closure<DependencyExtension>) {
        DependencyExtension(lego).apply {
            this.setAuthority(authority)
            dependencies.add(this)
            ConfigureUtil.configure(closure, this)
        }
    }

    override fun toString() = "DependenciesExtension: dependencies:$dependencies "
}