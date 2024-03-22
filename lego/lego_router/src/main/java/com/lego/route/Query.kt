package com.lego.route

/**
 * The Query of lego router.
 * Provide the mapping of original data type.
 *
 * @since 1.0
 */
class Query(
    val name: String,
    val value: Any?,
    val type: QueryType = QueryType.ANY
)

enum class QueryType {
    ANY,
    PARCELABLE,
    SERIALIZABLE,
    VALUE
}

