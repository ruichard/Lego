package com.lego.plugins.basic.utility

import BY_VERSION
import com.lego.annotations.source.RGenerated
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.TypeSpec

fun TypeSpec.Builder.addRGeneratedAnnotation(kind: String) = apply {
    addAnnotation(makeAnnotation(kind))
}

fun FunSpec.Builder.addRGeneratedAnnotation(kind: String) = apply {
    addAnnotation(makeAnnotation(kind))
}

private fun makeAnnotation(kind: String) =
    AnnotationSpec.builder(RGenerated::class.java)
        .addMember("kind = %S", kind)
        .addMember("by = %S", "lego-root:${BY_VERSION}")
        .build()