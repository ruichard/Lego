package com.lego.apt.utility

import com.lego.annotations.context.REvent
import com.lego.annotations.context.assist.REventAssist
import com.lego.annotations.context.instance.REventInstance
import com.lego.annotations.route.RRoute
import com.lego.annotations.route.RValue
import com.lego.annotations.route.assist.RRouteAssist
import com.lego.annotations.route.function.RFunction
import com.lego.annotations.route.instance.RRouteInstance
import com.lego.annotations.route.page.RPage

private fun contextUri(context:String,defaultScheme: String?) =
    if (context.contains("://") || context.isBlank()|| defaultScheme.isNullOrBlank() ) context else "$defaultScheme://$context"

fun RRoute.contextUri(defaultScheme: String?) = contextUri(uri.ifBlank { context }, defaultScheme)
fun RFunction.contextUri(defaultScheme: String?) = contextUri(uri, defaultScheme)
fun RPage.contextUri(defaultScheme: String?) = contextUri(uri, defaultScheme)
fun RRouteInstance.contextUri(defaultScheme: String?) = contextUri(uri, defaultScheme)
fun RRouteAssist.contextUri(defaultScheme: String?) = contextUri(uri.ifBlank { context }, defaultScheme)
fun RValue.contextUri(defaultScheme: String?) = contextUri(uri.ifBlank { context }, defaultScheme)
fun REvent.contextUri(defaultScheme: String?) = contextUri(uri.ifBlank { context }, defaultScheme)
fun REventAssist.contextUri(defaultScheme: String?) = contextUri(uri.ifBlank { context }, defaultScheme)
fun REventInstance.contextUri(defaultScheme: String?) = contextUri(uri, defaultScheme)
