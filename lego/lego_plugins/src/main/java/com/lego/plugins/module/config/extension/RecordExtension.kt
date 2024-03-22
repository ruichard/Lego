package com.lego.plugins.module.config.extension

import com.lego.plugins.module.config.record.VariantConfigRecord

/**
 *  Lego module extension of gradle plugins.
 *
 *  @since 1.8
 */
open class RecordExtension(private val variantRecord: VariantConfigRecord) {

    fun token(value: String) {
        variantRecord.token = value
    }

}