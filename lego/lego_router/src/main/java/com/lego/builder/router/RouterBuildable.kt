package com.lego.builder.router

import com.lego.builder.query.DSLLaunchQueriesFrameable
import com.lego.builder.query.LinkedLaunchQueriesFrameable
import com.lego.builder.query.QueriesFrameable
import com.lego.builder.result.ResultsBuildable
import com.lego.route.Results
import com.lego.router.Router
import com.lego.router.annotations.RInvariant

interface RouterBuildable {
    fun checkRouterVersion(version: Int): RouterBuildable
    @RInvariant
    fun receiveResults(receive: (Results) -> Unit): RouterBuildable
    fun build(): Router
}

interface DSLRouterBuildable : RouterBuildable {
    var uri: String
    fun query(block: QueriesFrameable<Unit>.() -> Unit)
}

interface DSLLaunchRouterBuildable : RouterBuildable {
    var uri: String
    fun query(block: DSLLaunchQueriesFrameable.() -> Unit)
}

interface LinkedApiRouterBuildable : RouterBuildable, QueriesFrameable<LinkedApiRouterBuildable>, ResultsBuildable<LinkedApiRouterBuildable> {
    fun uri(uri: String): LinkedApiRouterBuildable
}

interface LinkedLaunchRouterBuildable : LinkedLaunchQueriesFrameable<LinkedLaunchRouterBuildable> {
    fun uri(uri: String): LinkedLaunchRouterBuildable
}
