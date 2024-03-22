package lego.generate.aggregate.demo_com_mars_lego_test_detail

import android.content.Context
import android.os.ResultReceiver
import android.view.View
import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.mars.util_library.TestLibDataBean
import com.lego.annotations.source.RGenerated
import com.lego.context.Aggregatable
import com.lego.context.AggregateFactory
import com.lego.route.mapping.caseToTypeOfT
import com.lego.route.mapping.toTypeOfT
import kotlin.Array
import kotlin.BooleanArray
import kotlin.Function0
import kotlin.Function1
import kotlin.Function2
import kotlin.Int
import kotlin.IntArray
import kotlin.Long
import kotlin.LongArray
import kotlin.String
import kotlin.Unit
import kotlin.collections.ArrayList
import kotlin.collections.List
import kotlin.collections.Map
import lego.generate.context.demo_com_mars_lego_test_detail.DetailRouteActions
import lego.generate.context.demo_com_mars_lego_test_detail.TestCreateBean
import lego.generate.context.demo_com_mars_lego_test_detail.TestDataBean
import lego.generate.context.demo_com_mars_lego_test_detail.TestListBean
import lego.generate.context.demo_com_mars_lego_test_detail.TestNullableBean
import lego.generate.context.demo_com_mars_lego_test_detail.TestParcelizeBean
import lego.generate.context.demo_com_mars_lego_test_detail.TestSerializableBean
import com.lego.activity.Launcher as LegoLauncher
import com.lego.route.Queries as LegoQueries
import com.lego.route.Result as LegoResult
import com.lego.route.ResultGroups as LegoResultGroups
import com.lego.router.uri.Path as LegoPath

/**
 * aggregate router function and router event of Lego Context.
 *
 * context uri: [demo://com.mars.lego-test.detail]
 * version: 0.1.4
 */
@RGenerated(
  kind = "aggregate",
  by = "lego-kapt:1.9.0.1.T-K1_3-LOCAL",
  version = "0.1.4"
)
@Keep
class DetailAggregate : Aggregatable, DetailRouteActions {
  override fun onEvent(msg: String, queries: LegoQueries) {
    when(msg){
      "MY_INIT" ->  {
        // com.mars.component.detail.event.EventsByInstance.onInit
        // - parameters:
        // --- arg1 : kotlin.Any
        // --- arg2 : kotlin.Any
        // --- arg3 : kotlin.Any
        // - resultType:
        // --- null
        val legoInstanceBool = queries.value(0, null)
        val arg1 = queries.value(1, null)
        val arg2 = queries.value(2, null)
        val arg3 = queries.value(3, null)
        com.mars.component.detail.event.provideEventsInstance(
              legoInstanceBool.caseToTypeOfT()
            ).onInit(
              arg1.caseToTypeOfT(),
              arg2.caseToTypeOfT(),
              arg3.caseToTypeOfT()
            )
      }
      "MY_DESTROY" ->  {
        // com.mars.component.detail.event.EventsByInstance.onDestroy
        // - parameters:
        // --- arg1 : kotlin.Any
        // --- arg2 : kotlin.Any
        // --- arg3 : kotlin.Any
        // - resultType:
        // --- null
        val legoInstanceBool = queries.value(0, null)
        val arg1 = queries.value(1, null)
        val arg2 = queries.value(2, null)
        val arg3 = queries.value(3, null)
        com.mars.component.detail.event.provideEventsInstance(
              legoInstanceBool.caseToTypeOfT()
            ).onDestroy(
              arg1.caseToTypeOfT(),
              arg2.caseToTypeOfT(),
              arg3.caseToTypeOfT()
            )
      }
      "LifeCycleEvent_Init" ->  {
        // com.mars.component.detail.event.EventsWithLambda.init
        // - resultType:
        // --- kotlin.Unit
        com.mars.component.detail.event.EventsWithLambda().init()
        // com.mars.component.detail.event.Events.onInit
        // - parameters:
        // --- context : android.content.Context
        // --- parameter1 : kotlin.String
        // - resultType:
        // --- null
        val context = queries.value(0, null)
        val parameter1 = queries.value(1, null)
        com.mars.component.detail.event.Events().onInit(
              context.caseToTypeOfT(),
              parameter1.caseToTypeOfT()
            )
      }
      "LifeCycleEvent_Destroy" ->  {
        // com.mars.component.detail.event.EventsWithLambda.destroy
        // - resultType:
        // --- kotlin.Unit
        com.mars.component.detail.event.EventsWithLambda().destroy()
        // com.mars.component.detail.event.Events.onDestroy
        // - parameters:
        // --- context : android.content.Context
        // --- parameter1 : kotlin.String
        // - resultType:
        // --- null
        val context = queries.value(0, null)
        val parameter1 = queries.value(1, null)
        com.mars.component.detail.event.Events().onDestroy(
              context.caseToTypeOfT(),
              parameter1.caseToTypeOfT()
            )
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
      "doTestGenericity7" == path ->  {
        val data = queries.value(0, null)
        doTestGenericity7(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "testGenericity4" == path ->  {
        testGenericity4().apply {
          results.set(0, LegoResult(this))
        }
      }
      "testGenericity5" == path ->  {
        val arg0 = queries.value(0, "arg0")
        testGenericity5(
          arg0.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestGenericity6" == path ->  {
        val data = queries.value(0, null)
        doTestGenericity6(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestGenericity1" == path ->  {
        val data = queries.value(0, null)
        doTestGenericity1(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestGenericity2" == path ->  {
        val data = queries.value(0, null)
        doTestGenericity2(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestGenericity3" == path ->  {
        val data = queries.value(0, null)
        doTestGenericity3(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestGenericity8" == path ->  {
        val resultSuccess = queries.value(0, null)
        doTestGenericity8(
              resultSuccess.caseToTypeOfT()
            )
      }
      "doTestGenericity9" == path ->  {
        val resultSuccess = queries.value(0, null)
        doTestGenericity9(
              resultSuccess.caseToTypeOfT()
            )
      }
      "doTestDataType1" == path ->  {
        val data = queries.value(0, null)
        doTestDataType1(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType2" == path ->  {
        val data = queries.value(0, null)
        doTestDataType2(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType3" == path ->  {
        val data = queries.value(0, null)
        doTestDataType3(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType4" == path ->  {
        val data = queries.value(0, null)
        doTestDataType4(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType5" == path ->  {
        val data = queries.value(0, null)
        doTestDataType5(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType6" == path ->  {
        val data = queries.value(0, null)
        doTestDataType6(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType7" == path ->  {
        val data = queries.value(0, null)
        doTestDataType7(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType8" == path ->  {
        val data = queries.value(0, null)
        doTestDataType8(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType9" == path ->  {
        val data = queries.value(0, null)
        doTestDataType9(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType10" == path ->  {
        val resultSuccess = queries.value(0, null)
        val resultSuccess2 = queries.value(1, null)
        doTestDataType10(
              resultSuccess.caseToTypeOfT(),
              resultSuccess2.caseToTypeOfT()
            )
      }
      "doTestDataType11" == path ->  {
        val resultSuccess : (kotlin.Int, lego.generate.context.demo_com_mars_lego_test_detail.TestNullableBean) -> kotlin.Unit = { arg0,arg1 ->
          results.set(0, LegoResult(arg0),LegoResult(arg1))
        }
        val resultSuccess2 : (lego.generate.context.demo_com_mars_lego_test_detail.TestNullableBean?) -> kotlin.Unit = { arg0 ->
          results.set(1, LegoResult(arg0))
        }
        doTestDataType11(
              resultSuccess,
              resultSuccess2
            )
      }
      "doTestDataType13" == path ->  {
        val data = queries.value(0, null)
        doTestDataType13(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType14" == path ->  {
        val data = queries.value(0, null)
        doTestDataType14(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType15" == path ->  {
        val data = queries.value(0, null)
        doTestDataType15(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType16" == path ->  {
        val data = queries.value(0, null)
        doTestDataType16(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType17" == path ->  {
        val data = queries.value(0, null)
        doTestDataType17(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "doTestDataType18" == path ->  {
        val data = queries.value(0, null)
        doTestDataType18(
          data.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "test-bean/create" == path ->  {
        val d1 = queries.value(0, null)
        val d2 = queries.value(1, null)
        val legoReceiveResult0 : (lego.generate.context.demo_com_mars_lego_test_detail.TestCreateBean) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        testBeanCreate(
              d1.caseToTypeOfT(),
              d2.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      "do-sth-ext" == path ->  {
        val i = queries.value(0, null)
        val s = queries.value(1, null)
        val legoReceiveResult0 : (kotlin.Int) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        doSthExt(
              i.caseToTypeOfT(),
              s.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      "doSthCompanion" == path ->  {
        val i = queries.value(0, null)
        val j = queries.value(1, null)
        val k = queries.value(2, null)
        val legoReceiveResult0 : (kotlin.String) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        doSthCompanion(
              i.caseToTypeOfT(),
              j.caseToTypeOfT(),
              k.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      "property/companion" == path ->  {
        val legoReceiveResult0 : (kotlin.String) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        propertyCompanion(
              legoReceiveResult0
            )
      }
      "doSthHOFCompanion" == path ->  {
        val arg0 = queries.value(0, "arg0")
        val legoReceiveResult0 : (kotlin.Int) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        doSthHOFCompanion(
              arg0.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      "2.0/do-sth-hof" == path ->  {
        val arg0 = queries.value(0, "arg0")
        val arg1 = queries.value(1, "arg1")
        val arg2 = queries.value(2, "arg2")
        doSthHof(
          arg0.caseToTypeOfT(),
          arg1.caseToTypeOfT(),
          arg2.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "1.0/property/property" == path ->  {
        val legoReceiveResult0 : (kotlin.String) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        propertyProperty(
              legoReceiveResult0
            )
      }
      "do-sth" == path ->  {
        doSth()
      }
      "view/get" == path ->  {
        val context = queries.value(0, null)
        val legoReceiveResult0 : ((android.view.View?) -> kotlin.Unit)? = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        viewGet(
              context.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      LegoPath("sth/{id}/a-{name}?code1={code1}&code2={code2}").matching(path) ->  {
        queries.addAll(LegoPath("sth/{id}/a-{name}?code1={code1}&code2={code2}").getParameters(path))
        val id = queries.value(0, null)
        val name = queries.value(1, null)
        val code1 = queries.value(2, null)
        val code2 = queries.value(3, null)
        val legoReceiveResult0 : (kotlin.String) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        sthIdAName(
              id.caseToTypeOfT(),
              name.caseToTypeOfT(),
              code1.caseToTypeOfT(),
              code2.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      LegoPath("sth-navigation-only/{uri}").matching(path) ->  {
        queries.addAll(LegoPath("sth-navigation-only/{uri}").getParameters(path))
        val uriLegoParameter = queries.value(0, null)
        val legoReceiveResult0 : (kotlin.String) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        sthNavigationOnlyUri(
              uriLegoParameter.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      "doSthVararg" == path ->  {
        val no = queries.value(0, null)
        val varargString = queries.value(1, null)
        doSthVararg(
              no.caseToTypeOfT(),
              *varargString.caseToTypeOfT()
            )
      }
      "doSthBean" == path ->  {
        val a1 = queries.value(0, null)
        val legoReceiveResult0 : (lego.generate.context.demo_com_mars_lego_test_detail.TestListBean) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        doSthBean(
              a1.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      "doSthAsyncHOF" == path ->  {
        val hof : (kotlin.String, lego.generate.context.demo_com_mars_lego_test_detail.TestDataBean) -> kotlin.Unit = { arg0,arg1 ->
          results.set(0, LegoResult(arg0),LegoResult(arg1))
        }
        doSthAsyncHOF(
              hof
            )
      }
      "doSthAsyncHOFNullable" == path ->  {
        val hof2p = queries.value(0, null)
        val hof : ((kotlin.String, lego.generate.context.demo_com_mars_lego_test_detail.TestDataBean) -> kotlin.Unit)? = { arg0,arg1 ->
          results.set(0, LegoResult(arg0),LegoResult(arg1))
        }
        doSthAsyncHOFNullable(
              hof2p.caseToTypeOfT(),
              hof
            )
      }
      "doSthAsync2HOF" == path ->  {
        val hof : (kotlin.String, kotlin.Int) -> kotlin.Unit = { arg0,arg1 ->
          results.set(0, LegoResult(arg0),LegoResult(arg1))
        }
        val hof2 : (kotlin.String, kotlin.Int) -> kotlin.Unit = { arg0,arg1 ->
          results.set(1, LegoResult(arg0),LegoResult(arg1))
        }
        doSthAsync2HOF(
              hof,
              hof2
            )
      }
      "doSthAsyncOpen" == path ->  {
        val uriLegoParameter = queries.value(0, null)
        val resultsLegoParameter : ((kotlin.String?, kotlin.Int) -> kotlin.Unit)? = { arg0,arg1 ->
          results.set(0, LegoResult(arg0),LegoResult(arg1))
        }
        doSthAsyncOpen(
              uriLegoParameter.caseToTypeOfT(),
              resultsLegoParameter
            )
      }
      "do-sth-async-interface" == path ->  {
        val onResult : (kotlin.String?, kotlin.Int) -> kotlin.Unit = { arg0,arg1 ->
          results.set(0, LegoResult(arg0),LegoResult(arg1))
        }
        doSthAsyncInterface(
              onResult
            )
      }
      "do-sth-async-2-interface" == path ->  {
        val onResult : (kotlin.String?, kotlin.Int) -> kotlin.Unit = { arg0,arg1 ->
          results.set(0, LegoResult(arg0),LegoResult(arg1))
        }
        val onResult2 : (kotlin.String?, kotlin.Int) -> kotlin.Unit = { arg0,arg1 ->
          results.set(1, LegoResult(arg0),LegoResult(arg1))
        }
        val onResult3 : (lego.generate.context.demo_com_mars_lego_test_detail.TestDataBean?) -> kotlin.Unit = { arg0 ->
          results.set(2, LegoResult(arg0))
        }
        doSthAsync2Interface(
              onResult,
              onResult2,
              onResult3
            )
      }
      "doSthHOFTop" == path ->  {
        val arg0 = queries.value(0, "arg0")
        val legoReceiveResult0 : (kotlin.Unit) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        doSthHOFTop(
              arg0.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      "property/top" == path ->  {
        val legoReceiveResult0 : (kotlin.String) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        propertyTop(
              legoReceiveResult0
            )
      }
      "doSthTop" == path ->  {
        val ints = queries.value(0, null)
        val li = queries.value(1, null)
        val strings = queries.value(2, null)
        val ls = queries.value(3, null)
        val beans = queries.value(4, null)
        val lb = queries.value(5, null)
        val legoReceiveResult0 : (kotlin.collections.List<lego.generate.context.demo_com_mars_lego_test_detail.TestDataBean>) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        doSthTop(
              ints.caseToTypeOfT(),
              li.caseToTypeOfT(),
              strings.caseToTypeOfT(),
              ls.caseToTypeOfT(),
              beans.caseToTypeOfT(),
              lb.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      "do-sth-provide-instance-by-func" == path ->  {
        doSthProvideInstanceByFunc()
      }
      "do-sth-provide-instance-by-func2" == path ->  {
        doSthProvideInstanceByFunc2()
      }
      "do-sth-provide-instance-by-parameter-func" == path ->  {
        val legoInstanceValue = queries.value(0, null)
        val legoInstanceV0 = queries.value(1, null)
        val v1 = queries.value(2, null)
        val v2 = queries.value(3, null)
        val v3 = queries.value(4, null)
        doSthProvideInstanceByParameterFunc(
              legoInstanceValue.caseToTypeOfT(),
              legoInstanceV0.caseToTypeOfT(),
              v1.caseToTypeOfT(),
              v2.caseToTypeOfT(),
              v3.caseToTypeOfT()
            )
      }
      "do-sth-provide-instance-by-constructor" == path ->  {
        val legoInstanceValue = queries.value(0, null)
        val v1 = queries.value(1, null)
        val v2 = queries.value(2, null)
        val v3 = queries.value(3, null)
        doSthProvideInstanceByConstructor(
              legoInstanceValue.caseToTypeOfT(),
              v1.caseToTypeOfT(),
              v2.caseToTypeOfT(),
              v3.caseToTypeOfT()
            )
      }
      "doSthResultReceiver" == path ->  {
        val resultLegoParameter = queries.value(0, null)
        val legoReceiveResult0 : (android.os.ResultReceiver) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        doSthResultReceiver(
              resultLegoParameter.caseToTypeOfT(),
              legoReceiveResult0
            )
      }
      "live-data/get" == path ->  {
        val legoReceiveResult0 : (androidx.lifecycle.LiveData<kotlin.String>) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        liveDataGet(
              legoReceiveResult0
            )
      }
      "live-data-bean/get" == path ->  {
        val legoReceiveResult0 : (androidx.lifecycle.LiveData<lego.generate.context.demo_com_mars_lego_test_detail.TestDataBean>) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        liveDataBeanGet(
              legoReceiveResult0
            )
      }
      "live-data-bean-list/get" == path ->  {
        val legoReceiveResult0 : (androidx.lifecycle.LiveData<kotlin.collections.List<lego.generate.context.demo_com_mars_lego_test_detail.TestListBean>>) -> kotlin.Unit = { arg0 ->
          results.set(0, LegoResult(arg0))
        }
        liveDataBeanListGet(
              legoReceiveResult0
            )
      }
      "fragment/page1" == path ->  {
        fragmentPage1().apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/serialization/parcel-bean" == path ->  {
        val parcelBean = queries.value(0, null)
        apiSerializationParcelBean(
          parcelBean.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/serialization/parcel-array" == path ->  {
        val parcels = queries.value(0, null)
        apiSerializationParcelArray(
          parcels.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/serialization/parcel-list" == path ->  {
        val parcels = queries.value(0, null)
        apiSerializationParcelList(
          parcels.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/serialization/Serializable-bean" == path ->  {
        val serializableBean = queries.value(0, null)
        apiSerializationSerializableBean(
          serializableBean.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/bigdata/json-array" == path ->  {
        val array = queries.value(0, null)
        apiBigdataJsonArray(
          array.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/bigdata/parcel-array" == path ->  {
        val array = queries.value(0, null)
        apiBigdataParcelArray(
          array.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/bigdata/lib-array" == path ->  {
        val array = queries.value(0, null)
        apiBigdataLibArray(
          array.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/bigdata/json-list" == path ->  {
        val list = queries.value(0, null)
        apiBigdataJsonList(
          list.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/bigdata/parcel-list" == path ->  {
        val list = queries.value(0, null)
        apiBigdataParcelList(
          list.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/bigdata/lib-list" == path ->  {
        val list = queries.value(0, null)
        apiBigdataLibList(
          list.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      "api/bigdata/parcel-arraylist" == path ->  {
        val list = queries.value(0, null)
        apiBigdataParcelArraylist(
          list.caseToTypeOfT()
        ).apply {
          results.set(0, LegoResult(this))
        }
      }
      LegoPath("activity/page2/{key_str1}/{key_str2}/{key_str3}").matching(path) ->  {
        // com.mars.component.detail.ui.SecondPageActivity
        // - parameters:
        // --- key_str1 : kotlin.String?
        // --- key_str2 : kotlin.String?
        // --- key_str3 : kotlin.String?
        LegoLauncher().launch(com.mars.component.detail.ui.SecondPageActivity::class.java,queries,LegoPath("activity/page2/{key_str1}/{key_str2}/{key_str3}").getParameters(path),results)
      }
      "activity/page1_from_detail" == path ->  {
        // com.mars.component.detail.ui.FirstPageActivity
        // - parameters:
        // --- key_1_pa : com.mars.component.detail.value.TestCompanionBean?
        // --- key_2_pa_ar : kotlin.Array<com.mars.component.detail.value.TestCompanionBean>?
        // --- key_3_int : kotlin.Int
        // --- key_4_strs : kotlin.collections.List<kotlin.String>?
        // --- key_5_bean : com.mars.component.detail.value.TestDataBean?
        // --- key_a_ints : kotlin.collections.List<kotlin.Int>?
        // --- key_c_pa_li : kotlin.collections.List<com.mars.component.detail.value.TestCompanionBean>?
        // --- key_int_for_all : kotlin.Int?
        // --- key_int_for_detail : kotlin.Int?
        // --- uri : kotlin.String?
        LegoLauncher().launch(com.mars.component.detail.ui.FirstPageActivity::class.java,queries,null,results)
      }
      "activity/page1_from_home" == path ->  {
        // com.mars.component.detail.ui.FirstPageActivity
        // - parameters:
        // --- key_1_pa : com.mars.component.detail.value.TestCompanionBean?
        // --- key_2_pa_ar : kotlin.Array<com.mars.component.detail.value.TestCompanionBean>?
        // --- key_3_int : kotlin.Int
        // --- key_4_strs : kotlin.collections.List<kotlin.String>?
        // --- key_5_bean : com.mars.component.detail.value.TestDataBean?
        // --- key_a_ints : kotlin.collections.List<kotlin.Int>?
        // --- key_c_pa_li : kotlin.collections.List<com.mars.component.detail.value.TestCompanionBean>?
        // --- key_int_for_all : kotlin.Int?
        // --- key_int_for_home : kotlin.Int?
        // --- uri : kotlin.String?
        LegoLauncher().launch(com.mars.component.detail.ui.FirstPageActivity::class.java,queries,null,results)
      }
      "activity/page1" == path ->  {
        // com.mars.component.detail.ui.FirstPageActivity
        // - parameters:
        // --- key_1_pa : com.mars.component.detail.value.TestCompanionBean?
        // --- key_2_pa_ar : kotlin.Array<com.mars.component.detail.value.TestCompanionBean>?
        // --- key_3_int : kotlin.Int
        // --- key_4_strs : kotlin.collections.List<kotlin.String>?
        // --- key_5_bean : com.mars.component.detail.value.TestDataBean?
        // --- key_a_ints : kotlin.collections.List<kotlin.Int>?
        // --- key_c_pa_li : kotlin.collections.List<com.mars.component.detail.value.TestCompanionBean>?
        // --- uri : kotlin.String?
        LegoLauncher().launch(com.mars.component.detail.ui.FirstPageActivity::class.java,queries,null,results)
      }
      else -> { throw com.lego.route.exception.BadPathOrVersionException(path)}
    }
  }

  override fun doTestGenericity7(data: Array<Long?>?): Array<Long?>? {
    // com.mars.component.detail.test.TestGenericityTask.Companion.doTestGenericity7
    // - parameters:
    // --- data : kotlin.Array<kotlin.Long?>?
    // - resultType:
    // --- kotlin.Array<kotlin.Long?>?
    return com.mars.component.detail.test.TestGenericityTask.Companion.doTestGenericity7(
          data
        )
  }

  override fun testGenericity4(): List<String?>? {
    // com.mars.component.detail.test.TestGenericityTask.testGenericity4
    // - resultType:
    // --- kotlin.collections.List<kotlin.String?>
    return com.mars.component.detail.test.TestGenericityTask().testGenericity4
  }

  override fun testGenericity5(arg0: List<String?>?): List<String?>? {
    // com.mars.component.detail.test.TestGenericityTask.testGenericity5
    // - parameters:
    // --- arg0 : kotlin.collections.List<kotlin.String?>?
    // - resultType:
    // --- kotlin.collections.List<kotlin.String?>?
    return com.mars.component.detail.test.TestGenericityTask().testGenericity5(
          arg0
        )
  }

  override fun doTestGenericity6(data: Array<TestNullableBean?>?): Array<TestNullableBean?>? {
    // com.mars.component.detail.test.TestGenericityTask.doTestGenericity6
    // - parameters:
    // --- data : kotlin.Array<com.mars.component.detail.value.TestNullableBean?>?
    // - resultType:
    // --- kotlin.Array<com.mars.component.detail.value.TestNullableBean?>?
    return com.mars.component.detail.test.TestGenericityTask().doTestGenericity6(
          data.toTypeOfT()
        ).toTypeOfT()
  }

  override fun doTestGenericity1(data: List<TestNullableBean?>?): List<TestNullableBean?>? {
    // com.mars.component.detail.test.TestGenericityTaskKt.doTestGenericity1
    // - parameters:
    // --- data : kotlin.collections.List<com.mars.component.detail.value.TestNullableBean?>?
    // - resultType:
    // --- kotlin.collections.List<com.mars.component.detail.value.TestNullableBean?>?
    return com.mars.component.detail.test.doTestGenericity1(
          data.toTypeOfT()
        ).toTypeOfT()
  }

  override fun doTestGenericity2(data: LiveData<TestNullableBean?>?): LiveData<TestNullableBean?>? {
    // com.mars.component.detail.test.TestGenericityTaskKt.doTestGenericity2
    // - parameters:
    // --- data : androidx.lifecycle.LiveData<com.mars.component.detail.value.TestNullableBean?>?
    // - resultType:
    // --- androidx.lifecycle.LiveData<com.mars.component.detail.value.TestNullableBean?>?
    return com.mars.component.detail.test.doTestGenericity2(
          data.toTypeOfT()
        ).toTypeOfT()
  }

  override fun doTestGenericity3(data: Map<String, Long?>): Map<String, Long?>? {
    // com.mars.component.detail.test.TestGenericityTaskKt.doTestGenericity3
    // - parameters:
    // --- data : kotlin.collections.Map<kotlin.String, kotlin.Long?>
    // - resultType:
    // --- kotlin.collections.Map<kotlin.String, kotlin.Long?>
    return com.mars.component.detail.test.doTestGenericity3(
          data
        )
  }

  override fun doTestGenericity8(resultSuccess: Function1<ArrayList<String>, Unit>) {
    // com.mars.component.detail.test.TestGenericityTaskKt.doTestGenericity8
    // - parameters:
    // --- resultSuccess : kotlin.Function1<kotlin.collections.ArrayList<kotlin.String>, kotlin.Unit>
    // - resultType:
    // --- null
    com.mars.component.detail.test.doTestGenericity8(
          resultSuccess
        )
  }

  override fun doTestGenericity9(resultSuccess: Function1<List<String>, Unit>) {
    // com.mars.component.detail.test.TestGenericityTaskKt.doTestGenericity9
    // - parameters:
    // --- resultSuccess : kotlin.Function1<kotlin.collections.List<kotlin.String>, kotlin.Unit>
    // - resultType:
    // --- null
    com.mars.component.detail.test.doTestGenericity9(
          resultSuccess
        )
  }

  override fun doTestDataType1(data: Context?): Context? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType1
    // - parameters:
    // --- data : android.content.Context?
    // - resultType:
    // --- android.content.Context?
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType1(
          data
        )
  }

  override fun doTestDataType2(data: Int?): Int? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType2
    // - parameters:
    // --- data : kotlin.Int?
    // - resultType:
    // --- kotlin.Int?
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType2(
          data
        )
  }

  override fun doTestDataType3(data: String?): String? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType3
    // - parameters:
    // --- data : kotlin.String?
    // - resultType:
    // --- kotlin.String?
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType3(
          data
        )
  }

  override fun doTestDataType4(data: TestNullableBean?): TestNullableBean? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType4
    // - parameters:
    // --- data : com.mars.component.detail.value.TestNullableBean?
    // - resultType:
    // --- com.mars.component.detail.value.TestNullableBean?
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType4(
          data.toTypeOfT()
        ).toTypeOfT()
  }

  override fun doTestDataType5(data: List<String>?): List<String>? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType5
    // - parameters:
    // --- data : kotlin.collections.List<kotlin.String>?
    // - resultType:
    // --- kotlin.collections.List<kotlin.String>?
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType5(
          data
        )
  }

  override fun doTestDataType6(data: Array<Long>?): Array<Long>? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType6
    // - parameters:
    // --- data : kotlin.Array<kotlin.Long>?
    // - resultType:
    // --- kotlin.Array<kotlin.Long>?
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType6(
          data
        )
  }

  override fun doTestDataType7(data: LongArray?): LongArray? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType7
    // - parameters:
    // --- data : kotlin.LongArray?
    // - resultType:
    // --- kotlin.LongArray?
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType7(
          data
        )
  }

  override fun doTestDataType8(data: IntArray?): IntArray? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType8
    // - parameters:
    // --- data : kotlin.IntArray?
    // - resultType:
    // --- kotlin.IntArray?
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType8(
          data
        )
  }

  override fun doTestDataType9(data: BooleanArray?): BooleanArray? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType9
    // - parameters:
    // --- data : kotlin.BooleanArray?
    // - resultType:
    // --- kotlin.BooleanArray?
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType9(
          data
        )
  }

  override fun doTestDataType10(resultSuccess: Function1<Int, Unit>,
      resultSuccess2: Function1<String, Unit>) {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType10
    // - parameters:
    // --- resultSuccess : kotlin.Function1<kotlin.Int, kotlin.Unit>
    // --- resultSuccess2 : kotlin.Function1<kotlin.String, kotlin.Unit>
    // - resultType:
    // --- null
    com.mars.component.detail.test.TestDataTypeTask().doTestDataType10(
          resultSuccess,
          resultSuccess2
        )
  }

  override fun doTestDataType11(resultSuccess: (Int, TestNullableBean) -> Unit,
      resultSuccess2: (TestNullableBean?) -> Unit) {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType11
    // - parameters:
    // --- resultSuccess : kotlin.Function2<kotlin.Int, com.mars.component.detail.value.TestNullableBean, kotlin.Unit>
    // --- resultSuccess2 : kotlin.Function1<com.mars.component.detail.value.TestNullableBean?, kotlin.Unit>
    // - resultType:
    // --- null
    val resultSuccessLegoResultTransform : (kotlin.Any?,kotlin.Any?) -> Unit = { arg0,arg1 -> 
      resultSuccess(
            arg0.caseToTypeOfT(),
            arg1.toTypeOfT()
          )
    }
    val resultSuccess2LegoResultTransform : (kotlin.Any?) -> Unit = { arg0 -> 
      resultSuccess2(
            arg0.toTypeOfT()
          )
    }
    com.mars.component.detail.test.TestDataTypeTask().doTestDataType11(
          resultSuccessLegoResultTransform,
          resultSuccess2LegoResultTransform
        )
  }

  override fun doTestDataType13(data: List<Int?>): List<Int?>? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType13
    // - parameters:
    // --- data : kotlin.collections.List<kotlin.Int?>
    // - resultType:
    // --- kotlin.collections.List<kotlin.Int?>
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType13(
          data
        )
  }

  override fun doTestDataType14(data: ArrayList<Int?>): ArrayList<Int?>? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType14
    // - parameters:
    // --- data : kotlin.collections.ArrayList<kotlin.Int?>
    // - resultType:
    // --- kotlin.collections.ArrayList<kotlin.Int?>
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType14(
          data
        )
  }

  override fun doTestDataType15(data: List<TestNullableBean?>): List<TestNullableBean?>? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType15
    // - parameters:
    // --- data : kotlin.collections.List<com.mars.component.detail.value.TestNullableBean?>
    // - resultType:
    // --- kotlin.collections.List<com.mars.component.detail.value.TestNullableBean?>
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType15(
          data.toTypeOfT()
        ).toTypeOfT()
  }

  override fun doTestDataType16(data: ArrayList<TestNullableBean?>): ArrayList<TestNullableBean?>? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType16
    // - parameters:
    // --- data : kotlin.collections.ArrayList<com.mars.component.detail.value.TestNullableBean?>
    // - resultType:
    // --- kotlin.collections.ArrayList<com.mars.component.detail.value.TestNullableBean?>
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType16(
          data.toTypeOfT()
        ).toTypeOfT()
  }

  override fun doTestDataType17(data: List<String?>): List<String?>? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType17
    // - parameters:
    // --- data : kotlin.collections.List<kotlin.String?>
    // - resultType:
    // --- kotlin.collections.List<kotlin.String?>
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType17(
          data
        )
  }

  override fun doTestDataType18(data: ArrayList<String?>): ArrayList<String?>? {
    // com.mars.component.detail.test.TestDataTypeTask.doTestDataType18
    // - parameters:
    // --- data : kotlin.collections.ArrayList<kotlin.String?>
    // - resultType:
    // --- kotlin.collections.ArrayList<kotlin.String?>
    return com.mars.component.detail.test.TestDataTypeTask().doTestDataType18(
          data
        )
  }

  override fun testBeanCreate(
    d1: Int?,
    d2: String?,
    legoReceiveResult0: (TestCreateBean) -> Unit
  ) {
    // com.mars.component.detail.value.TestCreateBean.com.mars.component.detail.value.TestCreateBean
    // - parameters:
    // --- d1 : kotlin.Int?
    // --- d2 : kotlin.String?
    // - resultType:
    // --- com.mars.component.detail.value.TestCreateBean
    legoReceiveResult0(
          com.mars.component.detail.value.TestCreateBean(
          d1,
          d2
        ).toTypeOfT()
        )
  }

  override fun doSthExt(
    i: Int,
    s: String,
    legoReceiveResult0: (Int) -> Unit
  ) {
    // com.mars.component.detail.api.ApisExtKt.doSthExt
    // - parameters:
    // --- s : kotlin.String
    // --- i : kotlin.Int
    // - resultType:
    // --- kotlin.Int
    legoReceiveResult0(
          com.mars.component.detail.api.doSthExt(
          s,
          i
        )
        )
  }

  override fun doSthCompanion(
    i: Int,
    j: String,
    k: Long,
    legoReceiveResult0: (String) -> Unit
  ) {
    // com.mars.component.detail.api.AipsInCompanion.DetailCompanion.doSthCompanion
    // - parameters:
    // --- i : kotlin.Int
    // --- j : kotlin.String
    // --- k : kotlin.Long
    // - resultType:
    // --- kotlin.String
    legoReceiveResult0(
          com.mars.component.detail.api.AipsInCompanion.DetailCompanion.doSthCompanion(
          i,
          j,
          k
        )
        )
  }

  override fun propertyCompanion(legoReceiveResult0: (String) -> Unit) {
    // com.mars.component.detail.api.AipsInCompanion.propertyCompanion
    // - resultType:
    // --- kotlin.String
    legoReceiveResult0(
          com.mars.component.detail.api.AipsInCompanion.propertyCompanion
        )
  }

  override fun doSthHOFCompanion(arg0: Int, legoReceiveResult0: (Int) -> Unit) {
    // com.mars.component.detail.api.AipsInCompanion.doSthHOFCompanion
    // - parameters:
    // --- arg0 : kotlin.Int
    // - resultType:
    // --- kotlin.Int
    legoReceiveResult0(
          com.mars.component.detail.api.AipsInCompanion.doSthHOFCompanion(
          arg0
        )
        )
  }

  override fun doSthHof(
    arg0: Int,
    arg1: String,
    arg2: TestDataBean
  ): Int? {
    // com.mars.component.detail.api.Apis.doSthHOF
    // - parameters:
    // --- arg0 : kotlin.Int
    // --- arg1 : kotlin.String
    // --- arg2 : com.mars.component.detail.value.TestDataBean
    // - resultType:
    // --- kotlin.Int
    return com.mars.component.detail.api.Apis().doSthHOF(
          arg0,
          arg1,
          arg2.toTypeOfT()
        )
  }

  override fun propertyProperty(legoReceiveResult0: (String) -> Unit) {
    // com.mars.component.detail.api.Apis.property
    // - resultType:
    // --- kotlin.String
    legoReceiveResult0(
          com.mars.component.detail.api.Apis().property
        )
  }

  override fun doSth() {
    // com.mars.component.detail.api.Apis.doSth
    // - resultType:
    // --- null
    com.mars.component.detail.api.Apis().doSth()
  }

  override fun viewGet(context: Context, legoReceiveResult0: ((View?) -> Unit)?) {
    // com.mars.component.detail.api.Apis.getView
    // - parameters:
    // --- context : android.content.Context
    // - resultType:
    // --- android.view.View?
    if ( null!=legoReceiveResult0 ) {
      legoReceiveResult0(
            com.mars.component.detail.api.Apis().getView(
            context
          )
          )
    }
  }

  override fun sthIdAName(
    id: String,
    name: String,
    code1: String,
    code2: String,
    legoReceiveResult0: (String) -> Unit
  ) {
    // com.mars.component.detail.api.Apis.getSth
    // - parameters:
    // --- id : kotlin.String
    // --- name : kotlin.String
    // --- code1 : kotlin.String
    // --- code2 : kotlin.String
    // - resultType:
    // --- kotlin.String
    legoReceiveResult0(
          com.mars.component.detail.api.Apis().getSth(
          id,
          name,
          code1,
          code2
        )
        )
  }

  override fun doSthVararg(no: Int, vararg varargString: String) {
    // com.mars.component.detail.api.Apis.doSthVararg
    // - parameters:
    // --- no : kotlin.Int
    // --- varargString : vararg kotlin.Array<kotlin.String>
    // - resultType:
    // --- null
    com.mars.component.detail.api.Apis().doSthVararg(
          no,
          *varargString
        )
  }

  override fun doSthBean(a1: TestDataBean, legoReceiveResult0: (TestListBean) -> Unit) {
    // com.mars.component.detail.api.Apis.doSthBean
    // - parameters:
    // --- a1 : com.mars.component.detail.value.TestDataBean
    // - resultType:
    // --- com.mars.component.detail.value.TestListBean
    legoReceiveResult0(
          com.mars.component.detail.api.Apis().doSthBean(
          a1.toTypeOfT()
        ).toTypeOfT()
        )
  }

  override fun doSthAsyncHOF(hof: (String, TestDataBean) -> Unit) {
    // com.mars.component.detail.api.ApisAsyncReturn.doSthAsyncHOF
    // - parameters:
    // --- hof : kotlin.Function2<kotlin.String, com.mars.component.detail.value.TestDataBean, kotlin.Unit>
    // - resultType:
    // --- null
    val hofLegoResultTransform : (kotlin.Any?,kotlin.Any?) -> Unit = { arg0,arg1 -> 
      hof(
            arg0.caseToTypeOfT(),
            arg1.toTypeOfT()
          )
    }
    com.mars.component.detail.api.ApisAsyncReturn().doSthAsyncHOF(
          hofLegoResultTransform
        )
  }

  override fun doSthAsyncHOFNullable(hof2p: Function2<String, TestDataBean, Unit>?, hof: ((String,
      TestDataBean) -> Unit)?) {
    // com.mars.component.detail.api.ApisAsyncReturn.doSthAsyncHOFNullable
    // - parameters:
    // --- hof : kotlin.Function2<kotlin.String, com.mars.component.detail.value.TestDataBean, kotlin.Unit>?
    // --- hof2p : kotlin.Function2<kotlin.String, com.mars.component.detail.value.TestDataBean, kotlin.Unit>?
    // - resultType:
    // --- null
    val hofLegoResultTransform : (kotlin.Any?,kotlin.Any?) -> Unit = { arg0,arg1 -> 
      if ( null!=hof ) {
        hof(
              arg0.caseToTypeOfT(),
              arg1.toTypeOfT()
            )
      }
    }
    com.mars.component.detail.api.ApisAsyncReturn().doSthAsyncHOFNullable(
          hofLegoResultTransform,
          hof2p.toTypeOfT()
        )
  }

  override fun doSthAsync2HOF(hof: (String, Int) -> Unit, hof2: (String, Int) -> Unit) {
    // com.mars.component.detail.api.ApisAsyncReturn.doSthAsyncHOF
    // - parameters:
    // --- hof : kotlin.Function2<kotlin.String, kotlin.Int, kotlin.Unit>
    // --- hof2 : kotlin.Function2<kotlin.String, kotlin.Int, kotlin.Unit>
    // - resultType:
    // --- null
    com.mars.component.detail.api.ApisAsyncReturn().doSthAsyncHOF(
          hof,
          hof2
        )
  }

  override fun doSthAsyncOpen(uriLegoParameter: String, resultsLegoParameter: ((String?, Int) ->
      Unit)?) {
    // com.mars.component.detail.api.ApisAsyncReturn.doSthAsyncOpen
    // - parameters:
    // --- uri : kotlin.String
    // --- results : com.mars.component.detail.api.Callback?
    // - resultType:
    // --- null
    val resultsLegoParameterLegoResultTransform = object : com.mars.component.detail.api.Callback() {
      override fun onCall(v1: kotlin.String?,v2: kotlin.Int) {
        if ( null!=resultsLegoParameter ) {
          resultsLegoParameter(
                v1,
                v2
              )
        }
      }
    }
    com.mars.component.detail.api.ApisAsyncReturn().doSthAsyncOpen(
          uriLegoParameter,
          resultsLegoParameterLegoResultTransform
        )
  }

  override fun doSthAsyncInterface(onResult: (String?, Int) -> Unit) {
    // com.mars.component.detail.api.ApisAsyncReturn.doSthAsyncInterface
    // - parameters:
    // --- onResult : com.mars.component.detail.api.Callbackable
    // - resultType:
    // --- null
    val onResultLegoResultTransform = object : com.mars.component.detail.api.Callbackable {
      override fun onCall(v1: kotlin.String?,v2: kotlin.Int) {
        onResult(
              v1,
              v2
            )
      }
    }
    com.mars.component.detail.api.ApisAsyncReturn().doSthAsyncInterface(
          onResultLegoResultTransform
        )
  }

  override fun doSthAsync2Interface(
    onResult: (String?, Int) -> Unit,
    onResult2: (String?, Int) -> Unit,
    onResult3: (TestDataBean?) -> Unit
  ) {
    // com.mars.component.detail.api.ApisAsyncReturn.doSthAsync2Interface
    // - parameters:
    // --- onResult : com.mars.component.detail.api.Callbackable
    // --- onResult2 : com.mars.component.detail.api.Callbackable
    // --- onResult3 : com.mars.component.detail.api.BeanCallbackable
    // - resultType:
    // --- null
    val onResultLegoResultTransform = object : com.mars.component.detail.api.Callbackable {
      override fun onCall(v1: kotlin.String?,v2: kotlin.Int) {
        onResult(
              v1,
              v2
            )
      }
    }
    val onResult2LegoResultTransform = object : com.mars.component.detail.api.Callbackable {
      override fun onCall(v1: kotlin.String?,v2: kotlin.Int) {
        onResult2(
              v1,
              v2
            )
      }
    }
    val onResult3LegoResultTransform = object : com.mars.component.detail.api.BeanCallbackable {
      override fun onCall(v: com.mars.component.detail.value.TestDataBean?) {
        onResult3(
              v.toTypeOfT()
            )
      }
    }
    com.mars.component.detail.api.ApisAsyncReturn().doSthAsync2Interface(
          onResultLegoResultTransform,
          onResult2LegoResultTransform,
          onResult3LegoResultTransform
        )
  }

  override fun doSthHOFTop(arg0: Unit, legoReceiveResult0: (Unit) -> Unit) {
    // com.mars.component.detail.api.AipsInTopKt.doSthHOFTop
    // - parameters:
    // --- arg0 : kotlin.Unit
    // - resultType:
    // --- kotlin.Unit
    legoReceiveResult0(
          com.mars.component.detail.api.doSthHOFTop(
          arg0
        )
        )
  }

  override fun propertyTop(legoReceiveResult0: (String) -> Unit) {
    // com.mars.component.detail.api.AipsInTopKt.propertyTop
    // - resultType:
    // --- kotlin.String
    legoReceiveResult0(
          com.mars.component.detail.api.propertyTop
        )
  }

  override fun doSthTop(
    ints: Array<Int>,
    li: List<Int?>,
    strings: Array<String>,
    ls: List<String>,
    beans: Array<TestDataBean>,
    lb: List<TestDataBean>,
    legoReceiveResult0: (List<TestDataBean>) -> Unit
  ) {
    // com.mars.component.detail.api.AipsInTopKt.doSthTop
    // - parameters:
    // --- ints : kotlin.Array<kotlin.Int>
    // --- li : kotlin.collections.List<kotlin.Int?>
    // --- strings : kotlin.Array<kotlin.String>
    // --- ls : kotlin.collections.List<kotlin.String>
    // --- beans : kotlin.Array<com.mars.component.detail.value.TestDataBean>
    // --- lb : kotlin.collections.List<com.mars.component.detail.value.TestDataBean>
    // - resultType:
    // --- kotlin.collections.List<com.mars.component.detail.value.TestDataBean>
    legoReceiveResult0(
          com.mars.component.detail.api.doSthTop(
          ints,
          li,
          strings,
          ls,
          beans.toTypeOfT(),
          lb.toTypeOfT()
        ).toTypeOfT()
        )
  }

  override fun doSthProvideInstanceByFunc() {
    // com.mars.component.detail.api.Task.doSthProvideObject
    // - resultType:
    // --- null
    com.mars.component.detail.api.provideTask().doSthProvideObject()
  }

  override fun doSthProvideInstanceByFunc2() {
    // com.mars.component.detail.api.Task.doSthProvideObject2
    // - resultType:
    // --- null
    com.mars.component.detail.api.provideTask().doSthProvideObject2()
  }

  override fun doSthProvideInstanceByParameterFunc(
    legoInstanceValue: String,
    legoInstanceV0: Int?,
    v1: String,
    v2: Int?,
    v3: Int?
  ) {
    // com.mars.component.detail.api.Task.doSthProvideObject2
    // - parameters:
    // --- v1 : kotlin.String
    // --- v2 : kotlin.Int?
    // --- v3 : kotlin.Int?
    // - resultType:
    // --- null
    com.mars.component.detail.api.provideTask2(
          legoInstanceValue,
          legoInstanceV0
        ).doSthProvideObject2(
          v1,
          v2,
          v3
        )
  }

  override fun doSthProvideInstanceByConstructor(
    legoInstanceValue: String,
    v1: String,
    v2: Int?,
    v3: Int?
  ) {
    // com.mars.component.detail.api.Task.doSthProvideObject3
    // - parameters:
    // --- v1 : kotlin.String
    // --- v2 : kotlin.Int?
    // --- v3 : kotlin.Int?
    // - resultType:
    // --- null
    com.mars.component.detail.api.Task(
          legoInstanceValue
        ).doSthProvideObject3(
          v1,
          v2,
          v3
        )
  }

  override fun doSthResultReceiver(resultLegoParameter: ResultReceiver,
      legoReceiveResult0: (ResultReceiver) -> Unit) {
    // com.mars.component.detail.api.ApisWithResultReceiver.doSthResultReceiver
    // - parameters:
    // --- result : android.os.ResultReceiver
    // - resultType:
    // --- android.os.ResultReceiver
    legoReceiveResult0(
          com.mars.component.detail.api.ApisWithResultReceiver().doSthResultReceiver(
          resultLegoParameter
        )
        )
  }

  override fun liveDataGet(legoReceiveResult0: (LiveData<String>) -> Unit) {
    // com.mars.component.detail.api.ApisWithLiveData.getSthLiveData
    // - resultType:
    // --- androidx.lifecycle.LiveData<kotlin.String>
    legoReceiveResult0(
          com.mars.component.detail.api.ApisWithLiveData().getSthLiveData()
        )
  }

  override fun liveDataBeanGet(legoReceiveResult0: (LiveData<TestDataBean>) -> Unit) {
    // com.mars.component.detail.api.ApisWithLiveData.getSthLiveDataBean
    // - resultType:
    // --- androidx.lifecycle.LiveData<com.mars.component.detail.value.TestDataBean>
    legoReceiveResult0(
          com.mars.component.detail.api.ApisWithLiveData().getSthLiveDataBean().toTypeOfT()
        )
  }

  override fun liveDataBeanListGet(legoReceiveResult0: (LiveData<List<TestListBean>>) -> Unit) {
    // com.mars.component.detail.api.ApisWithLiveData.getSthLiveDataBeanList
    // - resultType:
    // --- androidx.lifecycle.LiveData<kotlin.collections.List<com.mars.component.detail.value.TestListBean>>
    legoReceiveResult0(
          com.mars.component.detail.api.ApisWithLiveData().getSthLiveDataBeanList().toTypeOfT()
        )
  }

  override fun fragmentPage1(): Fragment? {
    // com.mars.component.detail.ui.FirstPageFragment.com.mars.component.detail.ui.FirstPageFragment
    // - resultType:
    // --- com.mars.component.detail.ui.FirstPageFragment
    return com.mars.component.detail.ui.FirstPageFragment()
  }

  override fun apiSerializationParcelBean(parcelBean: TestParcelizeBean?): TestParcelizeBean? {
    // com.mars.component.detail.api.ApiSerialization.doSthParcelBean
    // - parameters:
    // --- parcelBean : com.mars.component.detail.value.TestParcelizeBean?
    // - resultType:
    // --- com.mars.component.detail.value.TestParcelizeBean
    return com.mars.component.detail.api.ApiSerialization().doSthParcelBean(
          parcelBean.toTypeOfT()
        ).toTypeOfT()
  }

  override fun apiSerializationParcelArray(parcels: Array<TestParcelizeBean>):
      Array<TestParcelizeBean>? {
    // com.mars.component.detail.api.ApiSerialization.doSthParcelArray
    // - parameters:
    // --- parcels : kotlin.Array<com.mars.component.detail.value.TestParcelizeBean>
    // - resultType:
    // --- kotlin.Array<com.mars.component.detail.value.TestParcelizeBean>
    return com.mars.component.detail.api.ApiSerialization().doSthParcelArray(
          parcels.toTypeOfT()
        ).toTypeOfT()
  }

  override fun apiSerializationParcelList(parcels: List<TestParcelizeBean>):
      List<TestParcelizeBean>? {
    // com.mars.component.detail.api.ApiSerialization.doSthParcelList
    // - parameters:
    // --- parcels : kotlin.collections.List<com.mars.component.detail.value.TestParcelizeBean>
    // - resultType:
    // --- kotlin.collections.List<com.mars.component.detail.value.TestParcelizeBean>
    return com.mars.component.detail.api.ApiSerialization().doSthParcelList(
          parcels.toTypeOfT()
        ).toTypeOfT()
  }

  override fun apiSerializationSerializableBean(serializableBean: TestSerializableBean):
      TestSerializableBean? {
    // com.mars.component.detail.api.ApiSerialization.doSthSerializableBean
    // - parameters:
    // --- serializableBean : com.mars.component.detail.value.TestSerializableBean
    // - resultType:
    // --- com.mars.component.detail.value.TestSerializableBean
    return com.mars.component.detail.api.ApiSerialization().doSthSerializableBean(
          serializableBean.toTypeOfT()
        ).toTypeOfT()
  }

  override fun apiBigdataJsonArray(array: Array<TestDataBean>): Array<TestDataBean>? {
    // com.mars.component.detail.api.ApiBigData.getBigJsonArray
    // - parameters:
    // --- array : kotlin.Array<com.mars.component.detail.value.TestDataBean>
    // - resultType:
    // --- kotlin.Array<com.mars.component.detail.value.TestDataBean>
    return com.mars.component.detail.api.ApiBigData().getBigJsonArray(
          array.toTypeOfT()
        ).toTypeOfT()
  }

  override fun apiBigdataParcelArray(array: Array<TestParcelizeBean>): Array<TestParcelizeBean>? {
    // com.mars.component.detail.api.ApiBigData.getBigParcelArray
    // - parameters:
    // --- array : kotlin.Array<com.mars.component.detail.value.TestParcelizeBean>
    // - resultType:
    // --- kotlin.Array<com.mars.component.detail.value.TestParcelizeBean>
    return com.mars.component.detail.api.ApiBigData().getBigParcelArray(
          array.toTypeOfT()
        ).toTypeOfT()
  }

  override fun apiBigdataLibArray(array: Array<TestLibDataBean>): Array<TestLibDataBean>? {
    // com.mars.component.detail.api.ApiBigData.getBigLibArray
    // - parameters:
    // --- array : kotlin.Array<com.mars.util_library.TestLibDataBean>
    // - resultType:
    // --- kotlin.Array<com.mars.util_library.TestLibDataBean>
    return com.mars.component.detail.api.ApiBigData().getBigLibArray(
          array
        )
  }

  override fun apiBigdataJsonList(list: List<TestDataBean>): List<TestDataBean>? {
    // com.mars.component.detail.api.ApiBigData.getBigJsonList
    // - parameters:
    // --- list : kotlin.collections.List<com.mars.component.detail.value.TestDataBean>
    // - resultType:
    // --- kotlin.collections.List<com.mars.component.detail.value.TestDataBean>
    return com.mars.component.detail.api.ApiBigData().getBigJsonList(
          list.toTypeOfT()
        ).toTypeOfT()
  }

  override fun apiBigdataParcelList(list: List<TestParcelizeBean>): List<TestParcelizeBean>? {
    // com.mars.component.detail.api.ApiBigData.getBigParcelList
    // - parameters:
    // --- list : kotlin.collections.List<com.mars.component.detail.value.TestParcelizeBean>
    // - resultType:
    // --- kotlin.collections.List<com.mars.component.detail.value.TestParcelizeBean>
    return com.mars.component.detail.api.ApiBigData().getBigParcelList(
          list.toTypeOfT()
        ).toTypeOfT()
  }

  override fun apiBigdataLibList(list: List<TestLibDataBean>): List<TestLibDataBean>? {
    // com.mars.component.detail.api.ApiBigData.getBigLibList
    // - parameters:
    // --- list : kotlin.collections.List<com.mars.util_library.TestLibDataBean>
    // - resultType:
    // --- kotlin.collections.List<com.mars.util_library.TestLibDataBean>
    return com.mars.component.detail.api.ApiBigData().getBigLibList(
          list
        )
  }

  override fun apiBigdataParcelArraylist(list: List<TestParcelizeBean>): List<TestParcelizeBean>? {
    // com.mars.component.detail.api.ApiBigData.getBigParcelArrayList
    // - parameters:
    // --- list : kotlin.collections.List<com.mars.component.detail.value.TestParcelizeBean>
    // - resultType:
    // --- kotlin.collections.List<com.mars.component.detail.value.TestParcelizeBean>
    return com.mars.component.detail.api.ApiBigData().getBigParcelArrayList(
          list.toTypeOfT()
        ).toTypeOfT()
  }

  override fun sthNavigationOnlyUri(uriLegoParameter: String, legoReceiveResult0: (String) ->
      Unit) {
    // com.mars.component.detail.api.Apis.getSthNavigationOnly
    // - parameters:
    // --- uri : kotlin.String
    // - resultType:
    // --- kotlin.String
    legoReceiveResult0(
          com.mars.component.detail.api.Apis().getSthNavigationOnly(
          uriLegoParameter
        )
        )
  }

  @RGenerated(
    kind = "aggregate_companion",
    by = "lego-kapt:1.9.0.1.T-K1_3-LOCAL",
    version = "0.1.4"
  )
  @Keep
  companion object : AggregateFactory() {
    override val URI: String = "demo://com.mars.lego-test.detail"

    override val DEPENDENCIES: List<String> = listOf()

    override val EVENT_MSGS: List<String> =
        listOf("MY_INIT","MY_DESTROY","LifeCycleEvent_Init","LifeCycleEvent_Destroy")

    override val CREATOR: Function0<Aggregatable> = {DetailAggregate()}
  }
}
