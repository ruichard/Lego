package com.lego.test.router

import com.lego.context.Aggregatable
import com.lego.context.AggregateCompanion
import com.lego.route.Queries
import com.lego.route.Result
import com.lego.route.Results

class TestSimpleAggregate : Aggregatable {
    override fun onEvent(msg: String, queries: Queries) {
        print(msg + queries)
    }

    override fun onRoute(path: String, queries: Queries, results: List<Results>) {
        print(path + queries + results)
        results.getOrNull(0)?.setOne(Result(100))
    }

    companion object : AggregateCompanion() {
        override val URI: String = "a://b"

        override val DEPENDENCIES: List<String> = listOf()

        override val EVENT_MSGS: List<String> = listOf()

        override val CREATOR = { TestSimpleAggregate() }
    }
}