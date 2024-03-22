package com.lego.route.mapping

import android.content.Intent
import android.os.Bundle
import com.lego.router.annotations.RInvariant
import java.lang.reflect.Type

const val KEY_JSON_VALUE_PREFIX = "R_KEY_JSON_VALUE_"

internal fun Bundle.isJsonExtras(key: String) = !getString("${KEY_JSON_VALUE_PREFIX}${key}").isNullOrBlank()

internal fun Bundle.putJson(key: String, value: Any?) {
    putString("${KEY_JSON_VALUE_PREFIX}$key", value.toJson())
}

@RInvariant
fun Intent.query(name: String, type: Type): Any? = extras?.query(name, type)

@RInvariant
fun Bundle.query(name: String, type: Type): Any? {
    return this.run {
        if (isJsonExtras(name)) {
            (getString("${KEY_JSON_VALUE_PREFIX}${name}") ?: "").jsonToType(type)
        } else {
            get(name).caseToTypeOfT()
        }
    }
}

fun <T> Intent.valueProperty(name: String, type: Type): T? = extras?.valueProperty(name, type)

fun <T> Bundle.valueProperty(name: String, type: Type): T? {
    return this.run {
        if (isJsonExtras(name)) {
            (getString("${KEY_JSON_VALUE_PREFIX}${name}") ?: "").jsonToType<T>(type)
        } else null
    }
}