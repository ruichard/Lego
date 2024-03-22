package com.lego.plugins.module.config.extension

import com.lego.plugins.module.config.record.ProjectConfigRecord
import com.lego.plugins.module.config.record.VariantConfigRecord
import groovy.lang.Closure
import org.gradle.util.ConfigureUtil

/**
 *  Lego module extension of gradle plugins.
 *
 *  @since 1.8
 */
open class PathExtension(private val projectRecord: ProjectConfigRecord) {

    fun byteCodeMode(value: Boolean){
        projectRecord.byteCodeModel = value
    }

    fun variant(value: String, closure: Closure<RecordExtension>) {
        VariantConfigRecord().let { variantRecord->
            RecordExtension(variantRecord).apply {
                ConfigureUtil.configure(closure, this)
            }
            projectRecord.variants.put(value,variantRecord)
        }

    }
}