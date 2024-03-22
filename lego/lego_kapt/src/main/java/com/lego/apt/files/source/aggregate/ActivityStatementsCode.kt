package com.lego.apt.files.source.aggregate

import com.lego.apt.Constants
import com.lego.apt.codebase.activity.ActivityCodeBase
import com.ktnail.x.uri.buildVersionPath
import com.squareup.kotlinpoet.FunSpec


fun FunSpec.Builder.addActivityOnRouteStatements(activities: Map<String, ActivityCodeBase>) = apply {
    activities.forEach { (_, activity) ->
        val path = buildVersionPath(activity.path, activity.version)
        if(Constants.Aggregate.isParameterPath(path))
            beginControlFlow("${Constants.Aggregate.PATH_CLASS_NAME_AS}(\"$path\").${Constants.Aggregate.METHOD_MATCHING_NAME}(${Constants.Aggregate.ROUTE_PARAMETER_PATH_NAME}) -> ")
        else
            beginControlFlow("\"$path\" == ${Constants.Aggregate.ROUTE_PARAMETER_PATH_NAME} -> ")
        Constants.KDoc.function(
            activity.className,
            activity.propertiesKDoc
        ).forEach {doc->
            addComment(doc)
        }
        addStatement(activity.startCode(path))
        endControlFlow()
    }
}