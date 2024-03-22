package com.mars.component.home.test.kotlin

import android.content.Context
import lego.generate.context.demo_com_mars_lego_test_detail_java.DetailJavaContext
import lego.generate.context.demo_com_mars_lego_test_detail_java.TestJavaBean

class TestKotlinInvokeActionFunctionJavaTask {
    fun invoke(context: Context, onFinish: (String) -> Unit) {

        val tag = " B FUNCTION TASK "

        DetailJavaContext.touch {
            onFinish(" XX DBG B touch DetailJavaContext.URI：touch!!!")
        }.miss {
            onFinish(" XX DBG B touch DetailJavaContext.URI：miss!!!")
        }

        DetailJavaContext.doSth()

        DetailJavaContext.propertyProperty { value -> onFinish("$tag NA DBG JAVA propertyProperty value:$value") }

        DetailJavaContext.doSthAsyncOpen {  v1, v2 ->
            onFinish("$tag NA DBG JAVA doSthAsyncOpen value:$v1,$v2")
        }

        DetailJavaContext.doSthAsyncInterface {  v1, v2 ->
            onFinish("$tag NA DBG JAVA doSthAsyncInterface value:$v1,$v2")
        }

        DetailJavaContext.doSthStatic(8,"asd",22L){value ->
            onFinish("$tag NA DBG JAVA doSthCompanion value:$value")
        }

        DetailJavaContext.propertyStatic { value -> onFinish("$tag NA DBG JAVA propertyCompanion value:$value") }

        DetailJavaContext.doSthBeanProvideInstanceByFunc("msg from HOME", TestJavaBean(8, null)) { value ->
            onFinish("$tag NA DBG JAVA doSthBean value:${value.d1}")
        }

    }
}