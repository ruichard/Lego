package com.lego.builder.router

import com.lego.builder.query.QueriesBuilder
import com.lego.route.mapping.caseToTypeOfT
import java.lang.reflect.Type

class LinkedApiRouterBuilder : BasicRouterBuilder(), LinkedApiRouterBuildable {
    override val queriesBuilder = QueriesBuilder()
    override fun createUri(): String = uri
    private var uri: String = ""

    // linked
    override fun uri(uri: String) = apply {
        this.uri = uri
    }

    override fun String.with(value: Any?) = this@LinkedApiRouterBuilder.apply {
        queriesBuilder.apply {
            this@with with value
        }
    }

    override fun result(onReceive: (Any?) -> Unit) = apply {
        receiveResults { results ->
            onReceive(
                results.value(0)
            )
        }
    }

    override fun result(onReceive: (Any?, Any?) -> Unit) = apply {
        receiveResults { results ->
            onReceive(
                results.value(0),
                results.value(1)
            )
        }
    }

    override fun result(
        onReceive: (Any?, Any?, Any?) -> Unit
    ) = apply {
        receiveResults { results ->
            onReceive(
                results.value(0),
                results.value(1),
                results.value(2)
            )
        }
    }

    override fun result(
        onReceive: (Any?, Any?, Any?, Any?) -> Unit
    ) = apply {
        receiveResults { results ->
            onReceive(
                results.value(0),
                results.value(1),
                results.value(2),
                results.value(3)

            )
        }
    }

    override fun result(
        onReceive: (Any?, Any?, Any?, Any?, Any?) -> Unit
    ) = apply {
        receiveResults { results ->
            onReceive(
                results.value(0),
                results.value(1),
                results.value(2),
                results.value(3),
                results.value(4)
            )
        }
    }

    override fun result(
        onReceive: (Any?, Any?, Any?, Any?, Any?, Any?) -> Unit
    ) = apply {
        receiveResults { results ->
            onReceive(
                results.value(0),
                results.value(1),
                results.value(2),
                results.value(3),
                results.value(4),
                results.value(5)
            )
        }
    }

    override fun result(
        onReceive: (Any?, Any?, Any?, Any?, Any?, Any?, Any?) -> Unit
    ) = apply {
        receiveResults { results ->
            onReceive(
                results.value(0),
                results.value(1),
                results.value(2),
                results.value(3),
                results.value(4),
                results.value(5),
                results.value(6)
            )
        }
    }
}