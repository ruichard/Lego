package com.lego.plugins.basic.exception.module

import com.lego.plugins.basic.exception.LegoException

/**
 * Thrown when module project node was not found. *
 * @since 1.8
 */
internal class LegoModuleProjectNodeNotFoundException(val path: String) :
    LegoException() {
    override fun toString() =
        "LegoModuleProjectNodeNotFoundException  module project [$path] node was not found!"
}