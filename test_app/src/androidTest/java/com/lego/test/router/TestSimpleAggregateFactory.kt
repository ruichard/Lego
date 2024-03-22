package com.lego.test.router

import com.lego.context.Aggregatable
import com.lego.context.AggregateFactory

class TestSimpleAggregateFactory : AggregateFactory {
    override fun create(uri: String): Aggregatable? {
        return TestSimpleAggregate()
    }

    override fun createByMsg(msg: String): List<Aggregatable> {
        return listOf(TestSimpleAggregate())
    }

    override fun touch(uri: String): Boolean {
        return true
    }

    override fun touchByMsg(msg: String): Boolean {
        return true
    }

}