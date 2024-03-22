package com.lego.route.mapping

import androidx.lifecycle.*
import com.lego.logger.Logger
import java.lang.reflect.Type

internal fun Any.toLiveData(type: Type): Any? {
    return if (this is LiveData<*>)
        type.firstTypeArgument?.let { liveType ->
            Transformations.map(this) { value ->
                value.toType(liveType)
            }.apply {
                Logger.da(" LEGO ") { " liveData rvalue : $this" }
            }
        }
    else
        null
}


