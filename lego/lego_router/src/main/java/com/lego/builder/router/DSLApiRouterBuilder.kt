package com.lego.builder.router

import com.lego.builder.query.QueriesBuilder
import com.lego.builder.query.QueriesFrameable
import com.lego.router.annotations.RInvariant

@RInvariant
class DSLApiRouterBuilder : BasicRouterBuilder() ,DSLRouterBuildable{
    override val queriesBuilder = QueriesBuilder()
    override fun createUri(): String = uri

    // DSL
    override var uri: String = ""

    override fun query(block: QueriesFrameable<Unit>.() -> Unit) {
        queriesBuilder.apply(block)
    }
}