package com.lego.plugins.basic.exception.module

import com.lego.plugins.basic.exception.LegoException

/**
 * Thrown when module project record was not found.
 *
 * @since 1.8
 */
internal class LegoModuleProjectRecordNotFoundException(val path:String) : LegoException() {
    override fun toString() =
        "LegoModuleProjectRecordNotFoundException  module project [$path] record was not found!"
}