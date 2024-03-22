package com.lego.route.exception

/**
 * Thrown when guess a value is parcelable, but no Creator found.
 *
 * @since 1.8
 */
internal class ParcelableNoCreatorException(private val clazz: String) : Exception() {
    override fun toString() = "ParcelableNoCreatorException guess [${clazz}] is parcelable, but no CREATOR found !"
}