package com.lego

import com.lego.context.Aggregatable
import com.lego.context.AggregateFactory
import com.lego.context.RouteActions
import com.lego.logger.Logger
import com.lego.route.exception.BadAggregatableClassException
import com.lego.route.exception.DuplicateRegisteredUriException
import com.lego.router.annotations.RInvariant
import com.lego.router.exception.BadLegoVersionException
import com.lego.router.exception.RContextNotFoundException
import com.lego.router.exception.LegoNotInitException
import kotlin.reflect.full.companionObjectInstance

/**
 *  The lego router object.
 *
 *  @since 1.0
 */
object Lego {
    /**
     * The logger of lego router.
     */
    fun logger(block: Logger.() -> Unit) {
        block(Logger)
    }

    private var aggregateFactories: MutableMap<String, () -> Aggregatable> = mutableMapOf()
    private var aggregateFactoriesByMsg: MutableMap<String, MutableSet<() -> Aggregatable>> = mutableMapOf()

    /**
     * register AggregateFactory to lego router.
     */
   internal fun registerAggregateFactory(factory: AggregateFactory) {
        if (!aggregateFactories.containsKey(factory.URI)) {
            aggregateFactories[factory.URI] = factory.CREATOR
            factory.EVENT_MSGS.forEach { msg ->
                aggregateFactoriesByMsg.getOrPut(msg) {
                    mutableSetOf()
                }.add(factory.CREATOR)
            }
        } else {
            throw DuplicateRegisteredUriException(factory.URI)
        }
    }

    /**
     * register Aggregatable to lego router by name.
     */
    @RInvariant
    fun registerAggregatable(className: String) {
        try {
            (Class.forName(className).kotlin.companionObjectInstance as? AggregateFactory).let {
                    companion ->
                if (null == companion)
                    throw BadAggregatableClassException(className)
                else
                    companion.register()
            }
        } catch (e: Exception) {
            Logger.e(" LEGO register on className: $className with exception : $e", e)
        }
    }

    @RInvariant
    fun createAggregate(uri: String): Aggregatable? {
        if (aggregateFactories.isEmpty()) {
            throw LegoNotInitException()
        }
        return aggregateFactories[uri]?.invoke()
    }

    internal fun createAggregatesByMsg(msg: String): List<Aggregatable> {
        if (aggregateFactories.isEmpty()) {
            throw LegoNotInitException()
        }
        return aggregateFactoriesByMsg[msg]?.map { creator ->
            creator()
        } ?: listOf()
    }

    @RInvariant
    inline fun <reified T : RouteActions> findActions(uri: String): T {
        return (createAggregate(uri) as? T) ?: throw RContextNotFoundException(uri)
    }

    internal fun touchUri(uri: String): Boolean {
        return aggregateFactories.containsKey(uri)
    }

    internal fun checkRouterVersionLogic(checkRouterVersion: Int) {
        if (checkRouterVersion < 900) {
            throw BadLegoVersionException("low version context or code [$checkRouterVersion] using a higher version router , this router version is 1.9.0 [900] + !")
        }
    }
}
