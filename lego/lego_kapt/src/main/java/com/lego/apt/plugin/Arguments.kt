package com.lego.apt.plugin

import javax.annotation.processing.ProcessingEnvironment

object Arguments {
    const val KAPT_GENERATED = "kapt.kotlin.generated"

    object Declare {
        const val JSON_CONTEXT = "lego.json.context"
        const val AGGREGATE_ENABLE = "lego.aggregate.enable"
        const val AGGREGATE_GENERATED = "lego.aggregate.generated"
        const val CONTEXT_ENABLE = "lego.context.enable"
        const val DEFAULT_SCHEME = "lego.default.scheme"
        const val CONTEXT_ROUTER_ENABLE = "lego.context.router.enable"
    }
}

fun ProcessingEnvironment.arguments(key: String): String? {
    return options?.get(key)
}

fun ProcessingEnvironment.booleanArguments(key: String): Boolean {
    return options?.get(key) == "true"
}
