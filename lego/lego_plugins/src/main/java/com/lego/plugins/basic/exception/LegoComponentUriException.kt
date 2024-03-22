package com.lego.plugins.basic.exception

/**
 * Thrown when defined component, but not set uri or uri ambiguity .
 *
 * @since 1.8
 */
internal open class LegoComponentUriException(
    private val uri: String,
    private val keyWord: String
) : LegoException() {
    override fun toString() =
        "LegoComponentElementRepeatException You can choose only one way from \"uri\" and " +
                "\"scheme + authority\" to define $keyWord[$uri] by only once."
}