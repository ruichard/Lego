package com.lego.builder.router

import com.lego.builder.query.DSLLaunchQueriesBuilder
import com.lego.builder.query.DSLLaunchQueriesFrameable

class DSLLaunchRouterBuilder(override val queriesBuilder: DSLLaunchQueriesBuilder) : BasicRouterBuilder(), DSLLaunchRouterBuildable {

    override fun createUri(): String =uri

    // DSL
    override var uri: String = ""

    override fun query(block: DSLLaunchQueriesFrameable.() -> Unit) {
        queriesBuilder.apply(block)
    }

}