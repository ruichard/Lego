package com.lego.route

import android.os.Binder
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Size
import android.util.SizeF
import com.lego.route.exception.BadQueryException
import com.lego.route.mapping.putJson
import java.io.Serializable

/**
 * The Queries of lego router.
 *
 * @see Query
 *
 * @since 1.0
 */
open class Queries {
    private val valuesList: MutableList<Query> = mutableListOf()

    fun add(query: Query) {
        valuesList.add(query)
    }

    fun addAll(values: List<Query>) {
        valuesList.addAll(values)
    }

    operator fun get(index: Int, name: String? = null): Query {
        return name?.let {
            valuesList.findLast { query -> query.name == name }
        } ?: valuesList.getOrNull(index) ?: throw BadQueryException(index, name)
    }

    fun value(
        index: Int,
        name: String? = null
    ): Any? {
        return this[index, name].value
    }

    fun toBundle() = Bundle(valuesList.size).apply {
        valuesList.forEachIndexed { _, query ->
            val key = query.name
            val value = query.value
            when (query.type) {
                QueryType.VALUE -> {
                    putJson(key, value)
                }
                QueryType.PARCELABLE -> {
                    if (!putParcelableQuery(key, value)) {
                        putJson(key, value)
                    }
                }
                QueryType.SERIALIZABLE -> {
                    if (!putSerializableQuery(key, value)) {
                        putJson(key, value)
                    }
                }
                else ->{
                    when (value) {
                        null -> putString(key, null) // Any nullable type will suffice.

                        // Scalars
                        is Boolean -> putBoolean(key, value)
                        is Byte -> putByte(key, value)
                        is Char -> putChar(key, value)
                        is Double -> putDouble(key, value)
                        is Float -> putFloat(key, value)
                        is Int -> putInt(key, value)
                        is Long -> putLong(key, value)
                        is Short -> putShort(key, value)

                        // References
                        is Bundle -> putBundle(key, value)
                        is CharSequence -> putCharSequence(key, value)

                        // Scalar arrays
                        is BooleanArray -> putBooleanArray(key, value)
                        is ByteArray -> putByteArray(key, value)
                        is CharArray -> putCharArray(key, value)
                        is DoubleArray -> putDoubleArray(key, value)
                        is FloatArray -> putFloatArray(key, value)
                        is IntArray -> putIntArray(key, value)
                        is LongArray -> putLongArray(key, value)
                        is ShortArray -> putShortArray(key, value)

                        // Reference arrays
                        is Array<*> -> {
                            if (!putReferenceArraysQuery(key, value)) {
                                putJson(key, value)
                            }
                        }

                        else -> {
                            if (Build.VERSION.SDK_INT >= 18 && value is Binder) {
                                putBinder(key, value)
                            } else if (Build.VERSION.SDK_INT >= 21 && value is Size) {
                                putSize(key, value)
                            } else if (Build.VERSION.SDK_INT >= 21 && value is SizeF) {
                                putSizeF(key, value)
                            } else {
                                putJson(key, value)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun Bundle.putParcelableQuery(key: String, value: Any?): Boolean {
        when (value) {
            is Parcelable -> {
                putParcelable(key, value)
                return true
            }
            is Array<*> -> {
                value::class.java.componentType?.let { componentType ->
                    if (Parcelable::class.java.isAssignableFrom(componentType)) {
                        @Suppress("UNCHECKED_CAST")  // Checked by reflection.
                        putParcelableArray(key, value as? Array<Parcelable>)
                        return true
                    }
                }
            }
            is ArrayList<*> -> {
                @Suppress("UNCHECKED_CAST")  // Checked by catch.
                putParcelableArrayList(key, value as? ArrayList<Parcelable>)
                return true
            }
        }
        return false
    }

    private fun Bundle.putSerializableQuery(key: String, value: Any?) :Boolean{
        return if (value is Serializable) { // all arrays are serializable.
            putSerializable(key, value)
            true
        } else {
            false
        }
    }

    private fun Bundle.putReferenceArraysQuery(key: String, value: Array<*>) :Boolean{
        value::class.java.componentType?.let {componentType->
            @Suppress("UNCHECKED_CAST") // Checked by reflection.
            when {
                String::class.java.isAssignableFrom(componentType) -> {
                    putStringArray(key, value as? Array<String>)
                    return true
                }
                CharSequence::class.java.isAssignableFrom(componentType) -> {
                    putCharSequenceArray(key, value as? Array<CharSequence>)
                    return true
                }
                else -> {}
            }
        }
        return false
    }
}

