package com.mars.component.home.test.kotlin

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import com.lego.router.bundleQueries
import com.lego.router.property
import lego.generate.context.demo_com_mars_lego_test_detail.DetailContext
import lego.generate.context.demo_com_mars_lego_test_detail.DetailRouteContext
import lego.generate.context.demo_com_mars_lego_test_detail.DetailRouteContext.Companion.doSthExt
import lego.generate.context.demo_com_mars_lego_test_detail.TestDataBean
import lego.generate.context.demo_com_mars_lego_test_detail.TestParcelizeBean

class TestKotlinInvokeRouteFunctionTask {
    fun invoke(context: Context, onFinish: (String) -> Unit) {

        val tag = " B FUNCTION TASK "

        DetailContext.touch {
            onFinish(" XX DBG B touch DetailContext：touch!!!")
        }.miss {
            onFinish(" XX DBG B touch DetailContext：miss!!!")
        }

        DetailRouteContext.doSth()

        val doSthHOFResult = DetailRouteContext.doSthHof(3, "123", TestDataBean(1, "456"))

        onFinish("$tag NA DBG doSthHOF doSthHOFResult:$doSthHOFResult")

        DetailRouteContext.propertyProperty { value -> onFinish("$tag NA DBG propertyProperty value:$value") }

        DetailRouteContext.doSthAsync2HOF({ v1, v2 ->
            onFinish("$tag NA DBG doSthAsyncHOF 1 value:$v1,$v2")
        }, { v1, v2 ->
            onFinish("$tag NA DBG doSthAsyncHOF 2 value:$v1,$v2")
        })

        DetailRouteContext.doSthAsyncOpen("textUri") {  v1, v2 ->
            onFinish("$tag NA DBG doSthAsyncOpen value:$v1,$v2")
        }

        DetailRouteContext.doSthAsyncInterface {  v1, v2 ->
            onFinish("$tag NA DBG doSthAsyncInterface value:$v1,$v2")
        }

        DetailRouteContext.viewGet(context) { view ->
            onFinish("$tag NA DBG getView value:$view")
        }

        DetailRouteContext.doSthCompanion(8,"asd",22L){value ->
            onFinish("$tag NA DBG doSthCompanion value:$value")
        }

        DetailRouteContext.propertyCompanion { value ->  onFinish("$tag NA DBG propertyCompanion value:$value")}

        DetailRouteContext.doSthHOFCompanion(6){ value ->
            onFinish("$tag NA DBG doSthHOFCompanion value:$value")
        }

        DetailRouteContext.doSthTop(
            arrayOf(1),
            listOf(2, 3),
            arrayOf("asd"),
            listOf("ghj", "zxc"),
            arrayOf(TestDataBean(100, "101")),
            listOf(TestDataBean(101, "102"),TestDataBean(103, "104"))
        ) { list ->
            list.forEach {
                onFinish("$tag NA DBG doSthTop d1:${it.d1} d1:${it.d2}")
            }
        }

        DetailRouteContext.doSthHOFTop(Unit) {

        }

        DetailRouteContext.propertyTop { value ->
            onFinish("$tag NA DBG propertyTop value:$value")
        }

        DetailRouteContext.doSthProvideInstanceByFunc()

        DetailRouteContext.doSthProvideInstanceByParameterFunc("2 value_ST 2", null, v1 = "2 value_ST- 2", v2 = 2888, v3 = 2999)

        DetailRouteContext.doSthProvideInstanceByConstructor("2 value_ST 3", v1 = "2 value_ST- 3", v2 = 3888, v3 = 3999)

        DetailRouteContext.doSthBean(TestDataBean(8, null)) { value ->
            onFinish("$tag NA DBG doSthBean value:${value.d1}")
        }

        DetailRouteContext.testBeanCreate(55, "ggghhh") { value ->
            onFinish("$tag NA DBG makeApiBean value:(${value.d1},${value.d2})")
        }

        val result = DetailRouteContext.Fragment.page1()
        onFinish("$tag NA DBG makeApiFragment value:$result")

        DetailRouteContext.sthIdAName("vbn", "ghj", "yui", "987") { value ->
            onFinish("$tag NA DBG getSth value:$value")
        }

        100.doSthExt("tmp"){
            onFinish("$tag NA DBG doSthExt value:$it")
        }

        DetailRouteContext.doSthResultReceiver(
            object : ResultReceiver(Handler()) {
                override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                    super.onReceiveResult(resultCode, resultData)
                    onFinish("$tag NA DBG doSthResultReceiver resultCode:$resultCode resultData1:${resultData?.property<String>(
                        "fromA"
                    )} resultData2:${resultData?.property<TestDataBean>(
                        "BEAN_fromA"
                    )?.d2}")
                }
            }
        ) { value ->
            value.send(1010, bundleQueries {
                "fromB" with " this is B "
                "BEAN_fromB" with TestDataBean(333,"B3w3w3w")
            })
            onFinish("$tag NA DBG doSthResultReceiver value:$value")
        }

        DetailRouteContext.doSthVararg(1, "qwe111", "asd2")

       val parcelizeBean = DetailRouteContext.Api.Serialization.parcelBean(TestParcelizeBean(1, "from home!"))
        onFinish("$tag NA DBG Serialization value:${parcelizeBean?.d2}")


    }
}