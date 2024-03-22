package com.lego.plugins.basic.exception

/**
 * Thrown when context dependency maven but no version set.
 *
 * @since 1.5
 */
internal class LegoKaptDependencyNotSetException : LegoException() {
    override fun toString() =
        "LegoKaptDependencyNotSetException  please make sure ext.lego_kapt_version in build.gradle of root project !"
}