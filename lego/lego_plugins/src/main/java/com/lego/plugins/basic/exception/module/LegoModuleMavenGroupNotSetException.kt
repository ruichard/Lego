package com.lego.plugins.basic.exception.module

import com.lego.plugins.basic.exception.LegoException

/**
 * Thrown when module maven groupId was not set.
 *
 * @since 1.8
 */
internal class LegoModuleMavenGroupNotSetException : LegoException() {
    override fun toString() =
        "LegoModuleMavenGroupNotSetException  please make sure ext.lego_module_maven_default_group_id in build.gradle of root project!"
}