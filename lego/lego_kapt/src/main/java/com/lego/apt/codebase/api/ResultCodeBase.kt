package com.lego.apt.codebase.api

import com.blueprint.kotlin.lang.type.KbpType
import javax.lang.model.type.TypeKind

/**
 * The code structure of Router Result.
 *
 * @since 1.1
 */
class ResultCodeBase(
    val name: String,
    type: KbpType
) : TypeCodeBase(type) {
    companion object {
        operator fun invoke(
            kbpType: KbpType
        ): ResultCodeBase? =
            if (kbpType.jmType?.kind != TypeKind.VOID)
                ResultCodeBase("", kbpType)
            else
                null
    }
}
