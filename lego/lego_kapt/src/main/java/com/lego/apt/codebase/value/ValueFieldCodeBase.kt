package com.lego.apt.codebase.value

import com.blueprint.kotlin.lang.type.KbpType
import com.lego.apt.codebase.AnnotationCodeBase
import com.lego.apt.codebase.api.TypeCodeBase

data class ValueFieldCodeBase(
    val name: String,
    private val kbpType: KbpType,
    val annotations: List<AnnotationCodeBase>,
    val defaultValueCode: String?,
    val isConstant: Boolean
) : TypeCodeBase(kbpType)
