package com.lego.apt.codebase.context

import com.ktnail.x.find
import com.ktnail.x.toPascal
import com.ktnail.x.uri.buildVersionPath
import com.lego.apt.Constants
import com.lego.apt.codebase.activity.ActivityCodeBase
import com.lego.apt.codebase.api.ApiCodeBase
import com.lego.apt.codebase.api.ApiInstanceCodeBase
import com.lego.apt.codebase.event.EventCodeBase
import com.lego.apt.codebase.event.EventInstanceCodeBase
import com.lego.apt.codebase.value.ValueCodeBase

/**
 * The code structure of Router Context.
 *
 * @since 1.1
 */
class ContextCodeBase {
    var name: String = ""
    var dependencies: List<String> = listOf()
    val events: MutableMap<String, MutableList<EventCodeBase>> = mutableMapOf() // key : msg
    val eventAssistants: MutableMap<String, EventInstanceCodeBase> = mutableMapOf() // key : tag
    val values: MutableList<ValueCodeBase> = mutableListOf()
    val sections = SectionCodeBase()
    val apis: MutableMap<String, ApiCodeBase> = mutableMapOf() // key : versionPath
    val apiAssistants: MutableMap<String, ApiInstanceCodeBase> = mutableMapOf() // key : versionPath
    val activities: MutableMap<String, ActivityCodeBase> = mutableMapOf()
    var version: String = "undefine"

    var generatedEnable: Boolean = false

    fun getAggregateName() = Constants.Aggregate.Declare.makeAggregateClassName(name)

    fun getContextName(): String = toPascal(name, Constants.Contexts.CONTEXT_BASE_CLASS_NAME)

    fun getRouteActionsName(): String = toPascal(name, Constants.RouteActions.CONTEXT_ROUTE_ACTIONS_BASE_CLASS_NAME)

    fun getRouteContextName(): String = toPascal(name, Constants.ContextRouters.ROUTE_CONTEXT_BASE_CLASS_NAME)

    fun merge(other: ContextCodeBase) {
        events.putAll(other.events)
        eventAssistants.putAll(other.eventAssistants)
        values.addAll(other.values)
        apis.putAll(other.apis)
        apiAssistants.putAll(other.apiAssistants)
        activities.putAll(other.activities)
    }

    fun addActivity(activity: ActivityCodeBase) {
        activities[buildVersionPath(activity.path, activity.version)] = activity
    }

    fun addApi(api: ApiCodeBase) {
        apis[buildVersionPath(api.path, api.version)] = api
    }

    fun compose() {
        composeSections()
        composeEventAssistants()
        composeApiAssistants()
    }

    private fun composeSections() {
        activities.forEach { (_, activity) ->
            sections.addItem(activity, activity.sections)

        }
        apis.forEach { (_, api) ->
            sections.addItem(api, api.sections)
        }
    }

    private fun composeEventAssistants() {
        eventAssistants.forEach { (tag, assistant) ->
            assistant.invoker.queries.forEach { query ->
                query.addNameAssistPrefix()
            }
            events.forEach { (_, msgEvents) ->
                msgEvents.filter { life -> life.tag == tag }.forEach { life ->
                    life.invoker.assistant = assistant.invoker
                }
            }
        }
    }

    private fun composeApiAssistants() {
        apiAssistants.forEach { (versionPath, assistant) ->
            assistant.invoker.queries.forEach { query ->
                query.addNameAssistPrefix()
            }
            if (assistant.version.isNotBlank())
                apis[versionPath]?.let { api ->
                    composeApiAssistant(api, assistant)
                }
            else
                apis.find { api -> api.path == assistant.forPath }.forEach { (_, api) ->
                    composeApiAssistant(api, assistant)
                }
        }

    }

    private fun composeApiAssistant(api: ApiCodeBase, apiAssistant: ApiInstanceCodeBase) {
        api.originalInvoker.assistant = apiAssistant.invoker
    }

}