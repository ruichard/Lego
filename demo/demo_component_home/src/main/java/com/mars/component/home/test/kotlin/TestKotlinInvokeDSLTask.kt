package com.mars.component.home.test.kotlin

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.view.View
import androidx.fragment.app.Fragment
import com.lego.route.mapping.query
import com.lego.router.*
import lego.generate.context.demo_com_mars_lego_test_detail.*

class TestKotlinInvokeDSLTask {
    fun invoke(context: Context, onFinish: (String) -> Unit) {
        val tag = " HOME DSL TASK "

        touch(uri = DetailContext.URI) {
            onFinish(" XX DBG HOME touch DetailContext.URI：touch!!!")
        }.miss {
            onFinish(" XX DBG HOME touch DetailContext.URI：miss!!!")
        }

        touch("xxx")  {
            onFinish(" XX DBG HOME touch xxx：touch!!!")
        }.miss {
            onFinish(" XX DBG HOME touch xxx：miss!!!")
        }

        navigate {
            uri = DetailContext.Uris.DO_STH
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_HOF
            query {
                "a0" with  4
                "a1" with "123"
                "a2" with TestDataBean(1, "456")
            }
            result<Int> { value ->
                onFinish("$tag NA DBG doSthHOF value:$value")
            }
        }

        val doSthHOFResult1: Int? = navigateForResult {
            uri = DetailContext.Uris.DO_STH_HOF
            query {
                "a0" with 9
                "a1" with "456"
                "a2" with TestDataBean(2, "567")
            }
        }

        val doSthHOFResult2 = navigateForResult<Int> {
            uri = DetailContext.Uris.DO_STH_HOF
            query {
                "a0" with  9
                "a1" with "456"
                "a2" with TestDataBean(2, "567")
            }
        }

        onFinish("$tag NA DBG doSthHOF doSthHOFResult1:$doSthHOFResult1 doSthHOFResult2:$doSthHOFResult2")

        navigate {
            uri = DetailContext.Uris.PROPERTY_PROPERTY
            result<String> { value ->
                onFinish("$tag NA DBG propertyProperty value:$value")
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_ASYNC_HOF
            result<String, TestDataBean> { v1, v2 ->
                onFinish("$tag NA DBG doSthAsyncHOF value:$v1,${v2.d2}")
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_ASYNC_OPEN
            query {
                "uri" with "textUri"
            }
            result<String, Int> { v1, v2 ->
                onFinish("$tag NA DBG doSthAsyncOpen value:$v1,$v2")
            }
        }


        navigate {
            uri = DetailContext.Uris.DO_STH_ASYNC_INTERFACE
            result<String, Int> { v1, v2 ->
                onFinish("$tag NA DBG doSthAsyncInterface value:$v1,$v2")
            }
        }

        navigate {
            uri = DetailContext.Uris.VIEW_GET
            query { "context" with context }
            result<View?> { view ->
                onFinish("$tag NA DBG getView value:$view")
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_COMPANION
            query {
                "i" with 5
                "j" with "abcdef"
                "k" with 99L
            }
            result<String> { value ->
                onFinish("$tag NA DBG doSthCompanion value:$value")
            }
        }

        navigate {
            uri = DetailContext.Uris.PROPERTY_COMPANION
            result<String> { value ->
                onFinish("$tag NA DBG propertyCompanion value:$value")
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_HOF_COMPANION
            query { "tmp" with 4 }
            result<Int> { value ->
                onFinish("$tag NA DBG doSthHOFCompanion value:$value")
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_TOP
            query {
                "1" with arrayOf(1)
                "2" with listOf(2, 3)
                "3" with arrayOf("asd")
                "4" with listOf("ghj", "zxc")
                "5" with arrayOf(TestDataBean(100, "101"))
                "6" with listOf(TestDataBean(101, "102"), TestDataBean(103, "104"))
            }
            result<List<TestDataBean>> { list ->
                list.forEach {
                    onFinish("$tag NA DBG doSthTop b1:${it.d1} d1:${it.d2}")
                }

            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_HOF_TOP
            query { "tmp" with Unit }
        }

        navigate {
            uri = DetailContext.Uris.PROPERTY_TOP
            result<String> { value ->
                onFinish("$tag NA DBG propertyTop value:$value")
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_PROVIDE_INSTANCE_BY_FUNC
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_PROVIDE_INSTANCE_BY_PARAMETER_FUNC
            query {
                "value" with "value_ST 2"
                "v0" with 255
                "v1" with "value_ST- 2"
                "v2" with 266
                "v3" with 277
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_PROVIDE_INSTANCE_BY_CONSTRUCTOR
            query {
                "value" with "value_ST 3"
                "v1" with "value_ST- 3"
                "v2" with 366
                "v3" with 377
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_BEAN
            query { "a1" with TestDataBean(99, "asd") }
            result<TestListBean> { value ->
                onFinish("$tag NA DBG doSthBean value:${value.d1}")
            }
        }

        navigate {
            uri = DetailContext.Uris.TEST_BEAN_CREATE
            query {
                "a" with 55
                "b" with "dddfff"
            }
            result<TestCreateBean> { value ->
                onFinish("$tag NA DBG makeApiBean value:(${value.d1},${value.d2})")
            }
        }

        navigate {
            uri = DetailContext.Uris.Fragment.PAGE1
            result<Fragment> { value ->
                onFinish("$tag NA DBG makeApiFragment value:$value")
            }
        }

        navigate {
            uri = DetailContext.Uris.STH_ID_A_NAME("123", "456", "qwe", "asd", "zxc", "tyu")
            result<String> { value ->
                onFinish("$tag NA DBG getSth value:$value")
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_EXT
            query {
                "i" with 99
                "s" with "tmp"
            }
            result<Int> { value ->
                onFinish("$tag NA DBG doSthExt value:$value")
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_RESULT_RECEIVER
            query {
                "result" with object : ResultReceiver(Handler()) {
                    override fun onReceiveResult(resultCode: Int, resultData: Bundle?) {
                        super.onReceiveResult(resultCode, resultData)
                        onFinish(
                            "$tag NA DBG doSthResultReceiver resultCode:$resultCode " + "resultData:${resultData?.property<String>(
                                "fromA"
                            )} resultData2:${resultData?.property<TestDataBean>(
                                "BEAN_fromA"
                            )?.d2}"
                        )
                    }
                }
            }
            result<ResultReceiver> { value ->
                value.send(1010, bundleQueries {
                    "fromB" with " this is HOME "
                    "BEAN_fromB" with TestDataBean(777,"B7y7y7y")

                })
                onFinish("$tag NA DBG doSthResultReceiver value:$value")
            }
        }

        navigate {
            uri = DetailContext.Uris.DO_STH_VARARG
            query {
                "no" with 2
                "strings" with arrayOf("qwe3", "asd4")
            }
        }

        navigate {
            uri = DetailContext.Uris.Api.Serialization.SERIALIZABLE_BEAN
            query {
                "bean" with  TestSerializableBean(2, "from home!")
            }
            result<TestSerializableBean> { value ->
                onFinish("$tag NA DBG SERIALIZABLE_BEAN value:${value.d2}")
            }
        }

        navigate {
            uri = DetailContext.Uris.Api.Serialization.PARCEL_BEAN
            query {
                "bean" with  TestParcelizeBean(1, "from home!")
            }
            result<TestParcelizeBean?> { value ->
                onFinish("$tag NA DBG PARCEL_BEAN value:${value?.d2}")
            }
        }

        navigate {
            uri = DetailContext.Uris.Api.Serialization.PARCEL_ARRAY
            query {
                "array" with  arrayOf(TestParcelizeBean(1, "from home!"))
            }
            result<Array<TestParcelizeBean>?> { value ->
                onFinish("$tag NA DBG PARCEL_ARRAY value size:${value?.size} 0 :${value?.getOrNull(0)?.d2}")
            }
        }

        navigate {
            uri = DetailContext.Uris.Api.Serialization.PARCEL_LIST
            query {
                "list" with  listOf(TestParcelizeBean(1, "from home!"))
            }
            result<List<TestParcelizeBean>?> { value ->
                onFinish("$tag NA DBG PARCEL_LIST value size:${value?.size} 0 :${value?.getOrNull(0)?.d2}")
            }
        }


    }
}