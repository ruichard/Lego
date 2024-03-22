package lego.generate.aggregate.demo_com_mars_lego_test_home

import androidx.annotation.Keep
import com.lego.annotations.source.RGenerated
import com.lego.context.Aggregatable
import com.lego.context.AggregateFactory
import com.lego.route.mapping.caseToTypeOfT
import com.lego.route.mapping.toTypeOfT
import kotlin.Function0
import kotlin.String
import kotlin.collections.List
import lego.generate.context.demo_com_mars_lego_test_home.HomeRouteActions
import com.lego.activity.Launcher as LegoLauncher
import com.lego.route.Queries as LegoQueries
import com.lego.route.Result as LegoResult
import com.lego.route.ResultGroups as LegoResultGroups
import com.lego.router.uri.Path as LegoPath

/**
 * aggregate router function and router event of Lego Context.
 *
 * context uri: [demo://com.mars.lego-test.home]
 * version: 0.1.4
 */
@RGenerated(
  kind = "aggregate",
  by = "lego-kapt:1.9.0.1.T-K1_3-LOCAL",
  version = "0.1.4"
)
@Keep
class HomeAggregate : Aggregatable, HomeRouteActions {
  override fun onEvent(msg: String, queries: LegoQueries) {
    when(msg){
      "LifeCycleEvent_Init" ->  {
        // com.mars.component.home.event.Events.myInit
        // - resultType:
        // --- null
        com.mars.component.home.event.Events().myInit()
      }
      "LifeCycleEvent_Destroy" ->  {
        // com.mars.component.home.event.Events.myDestory
        // - resultType:
        // --- null
        com.mars.component.home.event.Events().myDestory()
      }
      else -> {}
    }
  }

  override fun onRoute(
    path: String,
    queries: LegoQueries,
    results: LegoResultGroups
  ) {
    when {
      else -> { throw com.lego.route.exception.BadPathOrVersionException(path)}
    }
  }

  @RGenerated(
    kind = "aggregate_companion",
    by = "lego-kapt:1.9.0.1.T-K1_3-LOCAL",
    version = "0.1.4"
  )
  @Keep
  companion object : AggregateFactory() {
    override val URI: String = "demo://com.mars.lego-test.home"

    override val DEPENDENCIES: List<String> = listOf()

    override val EVENT_MSGS: List<String> = listOf("LifeCycleEvent_Init","LifeCycleEvent_Destroy")

    override val CREATOR: Function0<Aggregatable> = {HomeAggregate()}
  }
}
