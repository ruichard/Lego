package com.lego.plugins.basic.exception.module

import com.lego.plugins.basic.exception.LegoException

/**
 * Thrown when module maven version was not found.
 *
 * @since 1.8
 */
internal class LegoModuleMavenVersionUnknownException(val path: String,val variant: String) : LegoException() {
    override fun toString() = "LegoModuleMavenVersionUnknownException project:$path " +
            "variant:$variant module in bytecode mode , but maven version was not found!"
}