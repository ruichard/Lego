package com.lego.router

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ktnail.x.uri.Uri
import com.lego.Lego
import com.lego.builder.query.BundleQueriesBuilder
import com.lego.builder.query.BundleQueriesFrameable
import com.lego.builder.query.DSLLaunchQueriesBuilder
import com.lego.builder.router.*
import com.lego.context.Aggregatable
import com.lego.context.doEvent
import com.lego.logger.Logger
import com.lego.route.Queries
import com.lego.route.ResultGroups
import com.lego.route.Results
import com.lego.route.mapping.caseToTypeOfT
import com.lego.route.mapping.query
import com.lego.route.mapping.typeOfT
import com.lego.route.mapping.valueProperty
import com.lego.router.annotations.RInvariant
import com.lego.router.exception.RContextNotFoundException
import java.lang.reflect.Type

class Router internal constructor(
    private val uri: Uri,
    private val queries: Queries,
    private val resultGroups: ResultGroups,
    private val checkRouterVersion: Int? = null
) {

    private val path
        get() = uri.versionPath

    fun route() {
        safeRoute {
            checkRouterVersion()
            findAggregate(uri.basicUri).onRoute(path, queries, resultGroups)
        }
    }

    /**
     * route and get the result.
     */
    fun routeForResult(type: Type? = null): Any? {
        return routeSync().value(0).caseToTypeOfT()
    }

    @RInvariant
    fun routeSync(): Results {
        val results = Results(null)
        safeRoute {
            checkRouterVersion()
            resultGroups.load(results)
            findAggregate(uri.basicUri).onRoute(path, queries, resultGroups)
        }
        return results
    }

    private fun findAggregate(uri: String): Aggregatable {
        return Lego.createAggregate(uri) ?: throw RContextNotFoundException(uri)
    }

    private fun checkRouterVersion() {
        if (null != checkRouterVersion) Lego.checkRouterVersionLogic(checkRouterVersion)
    }

    private fun safeRoute(body: () -> Unit) {
        return try {
            body()
        } catch (e: Exception) {
            Logger.e(" LEGO navigation on uri: $uri with exception : $e", e)
        }
    }

    companion object {
        // event
        /**
         * start to sending a event message.
         */
        @JvmStatic
        fun doEvent(msg: String, vararg args: Any) {
            Lego.createAggregatesByMsg(msg).forEachIndexed { _, aggregate -> aggregate.doEvent(msg, *args) }
        }

        /**
         * start to sending a event message.
         */
        fun doEvent(msg: String, context: Context, vararg arg: Any) {
            doEvent(msg, *(listOf<Any>(context) + arg.toList()).toTypedArray())
        }

        // touch
        /**
         * touch other context by uri.
         *
         * @param  action invoke if can touch the context.
         */
        @JvmStatic
        fun touch(uri: String, action: () -> Unit) = TouchHolder(uri).touch(action)

        // builder
        /**
         * get a builder to build a route.
         */
        @JvmStatic
        fun builder(): LinkedApiRouterBuildable = LinkedApiRouterBuilder()

        /**
         * get a builder to build a route for starting a Activity.
         */
        @JvmStatic
        fun builder(context: Context): LinkedLaunchRouterBuilder =
            LinkedLaunchRouterBuilder().launchBy(context)

        /**
         * get a builder to build a route for starting a Activity by given Activity.
         */
        @JvmStatic
        fun builder(activity: Activity): LinkedLaunchRouterBuilder =
            LinkedLaunchRouterBuilder().launchBy(activity)

        /**
         * get a builder to build a route for starting a Activity by given Fragment.
         */
        @JvmStatic
        fun builder(fragment: Fragment): LinkedLaunchRouterBuilder =
            LinkedLaunchRouterBuilder().launchBy(fragment)

        // property
        /**
         * get a property value in Intent by name.
         */
        @JvmStatic
        fun <T> valueProperty(intent: Intent, name: String, type: Type): T? =
            intent.valueProperty(name, type)

        /**
         * get a property value in Bundle by name.
         */
        @JvmStatic
        fun <T> valueProperty(bundle: Bundle, name: String, type: Type): T? =
            bundle.valueProperty(name, type)
    }
}

// event
/**
 * start to sending a event message.
 */
fun Context.doEventWithContext(msg: String, vararg args: Any) = Router.doEvent(msg, this, *args)

/**
 * start to sending a event message.
 */
fun doEvent(msg: String, vararg args: Any) = Router.doEvent(msg, *args)

// touch
/**
 * touch other context by uri.
 *
 * @param  action invoke if can touch the context.
 */
fun touch(uri: String, action: () -> Unit) = Router.touch(uri, action)

// navigate
/**
 * navigate to a router path , using DSL code style.
 */
fun navigate(body: DSLRouterBuildable.() -> Unit): Unit =
    DSLApiRouterBuilder().apply(body).build().route()

/**
 * navigate to a router path , using DSL code style.
 */
fun Context.navigate(body: DSLLaunchRouterBuildable.() -> Unit): Unit =
    DSLLaunchRouterBuilder(DSLLaunchQueriesBuilder(this)).apply(body).build().route()

/**
 * navigate to a router path , using DSL code style , by given Activity.
 */
fun Activity.navigate(body: DSLLaunchRouterBuildable.() -> Unit): Unit =
    DSLLaunchRouterBuilder(DSLLaunchQueriesBuilder(this)).apply(body).build().route()

/**
 * navigate to a router path , using DSL code style , by given Fragment.
 */
fun Fragment.navigate(body: DSLLaunchRouterBuildable.() -> Unit): Unit =
    DSLLaunchRouterBuilder(DSLLaunchQueriesBuilder(this)).apply(body).build().route()

/**
 * navigate to a router path , using DSL code style , And receive results synchronously.
 */
@RInvariant
inline fun <reified T> navigateForResult(noinline body: DSLRouterBuildable.() -> Unit): T? {
    return safeRoute<T> {
        DSLApiRouterBuilder().apply(body).build().routeSync().value(0).caseToTypeOfT()
    }
}

// bundle/property
/**
 * get a property value in Intent by name.
 */
@RInvariant
inline fun <reified T> Bundle.property(name: String): T? = query(name, typeOfT<T>()).caseToTypeOfT()

@RInvariant
inline fun <reified T> Activity.property(name: String): T? =
    intent?.query(name, typeOfT<T>()).caseToTypeOfT()

@RInvariant
inline fun <reified T> Fragment.property(name: String): T? = activity?.property(name)

/**
 * add queries to bundle.
 */
fun bundleQueries(block: BundleQueriesFrameable<Unit>.() -> Unit) =
    BundleQueriesBuilder().apply(block).queries.toBundle()

@RInvariant
fun <T> safeRoute(body: () -> T): T? {
    return try {
        return body()
    } catch (e: Exception) {
        Logger.e(" LEGO navigation with exception : $e", e)
        null
    }
}