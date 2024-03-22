package com.lego.apt.files.source.context.route

import com.ktnail.x.uri.buildVersionUri
import com.lego.apt.Constants
import com.lego.apt.codebase.api.ApiCodeBase
import com.lego.apt.codebase.api.QueryCodeBase
import com.lego.apt.codebase.invoker.InvokeContextCodeBase
import com.lego.apt.files.source.context.addApiRouterParametersAndResult
import com.lego.apt.utility.addRGeneratedRouterAnnotation
import com.lego.apt.utility.inControlFlowStatementIf
import com.squareup.kotlinpoet.*

fun TypeSpec.Builder.addApiRouterDSLFunctions(
    uri: String,
    api: ApiCodeBase
) = apply {
    val functionName = api.contextFunctionName
    val apiUri = buildVersionUri(uri, api.path, api.version)
    val contextInvoker = api.contextInvoker(uri)
    addFunction(
        FunSpec.builder(
            functionName
        ).addApiRouterParametersAndResult(
            uri,
            contextInvoker,
            true
        ).addApiRouterDSLBody(
            apiUri,
            contextInvoker,
            functionName
        ).addAnnotation(
            JvmStatic::class.java
        ).addRGeneratedRouterAnnotation(
            apiUri,
            api.originalInvoker.type.toString(),
            api.originalInvoker.className,
            api.originalInvoker.name
        ).addKdoc(
            Constants.KDoc.functionRouter(
                apiUri,
                api.originalInvoker.location,
                api.originalInvoker.queriesKDoc,
                api.originalInvoker.resultKDoc
            )
        ).build()
    )
}

private fun FunSpec.Builder.addApiRouterDSLAddToQueryCode(query: QueryCodeBase, functionName: String) {
    if (!query.isExtendThis) {
        addStatement(Constants.Apis.makeAddToQueryCode(query.originalName, query.legalName))
    } else {
        addStatement(Constants.Apis.makeAddToQueryCode(query.originalName, "this@$functionName"))
    }
}

private fun FunSpec.Builder.addApiRouterDSLBody(
    apiUri: String,
    contextInvoker: InvokeContextCodeBase,
    functionName: String
) = apply {
    if (contextInvoker.hasSyncReturn) {
        beginControlFlow("return ${Constants.Apis.NAVIGATE_FOR_RESULT_FUNCTION_NAME}") // navigateForResult
    } else {
        beginControlFlow(Constants.Apis.NAVIGATE_FUNCTION_NAME) // navigate
    }

    addApiRouterDSLQueriesStatement(apiUri, functionName, contextInvoker)
    contextInvoker.resultParameters.forEach { resultLambdaType ->
        inControlFlowStatementIf(resultLambdaType.nullable, resultLambdaType.name) {
            addStatement("${Constants.Apis.RESULT_DSL_NAME}(${resultLambdaType.name})") // result()
        }
    }
    endControlFlow() // navigate or navigateForResult end
}

private fun FunSpec.Builder.addApiRouterDSLQueriesStatement(
    apiUri: String,
    functionName: String,
    contextInvoker: InvokeContextCodeBase
) = apply {
    addStatement("${Constants.Apis.URI_DSL_NAME}=\"$apiUri\"") // uri
    addStatement("${Constants.Apis.CHECK_ROUTER_VERSION_DSL_NAME}(${Constants.Router.CHECK_ROUTER_VERSION})") // checkRouterVersion
    if (contextInvoker.parameters.isNotEmpty() || contextInvoker.receiver != null) {
        beginControlFlow(Constants.Apis.QUERY_DSL_NAME) // query
        contextInvoker.receiver?.let {
            addApiRouterDSLAddToQueryCode(it, functionName)
        }
        contextInvoker.parameters.forEach {
            addApiRouterDSLAddToQueryCode(it, functionName)
        }
        endControlFlow() // query end
    }
}

