package com.lego.builder.query

import com.lego.route.Queries
import com.lego.route.Query
import com.lego.route.QueryType

open class QueriesBuilder : QueriesFrameable<Unit>, QueriesBuildable {
    open val queries: Queries = Queries()

    override fun buildQueries() = queries

    protected fun addQuery(name: String, value: Any?, type: QueryType = QueryType.ANY) {
        queries.add(Query(name, value, type))
    }

    override infix fun String.with(value: Any?) {
        addQuery(this, value)
    }
}
