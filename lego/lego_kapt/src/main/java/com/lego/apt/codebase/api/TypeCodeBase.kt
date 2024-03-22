package com.lego.apt.codebase.api

import com.blueprint.kotlin.lang.type.KbpType
import com.lego.apt.utility.containRValue
import com.lego.apt.utility.toRValueTypeName
import com.squareup.kotlinpoet.TypeName

open class TypeCodeBase(
    val originalType: KbpType
) {
    fun toContextTypeName(uri: String? = null): TypeName = originalType.toRValueTypeName(uri)

    val isRValue
        get() = originalType.containRValue()

    val nullabe
        get() = originalType.nullable
}