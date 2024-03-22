package com.mars.component.test

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.richard.kcomponent.MainActivity
import com.lego.Lego
import com.lego.router.navigate
import com.lego.router.result
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import lego.generate.context.demo_com_mars_lego_test_detail.DetailContext
import lego.generate.context.demo_com_mars_lego_test_detail.DetailContext.Companion.activityPage1
import lego.generate.context.demo_com_mars_lego_test_detail.DetailContext.Companion.activityPage2KeyStr1KeyStr2KeyStr3
import lego.generate.context.demo_com_mars_lego_test_detail.TestCompanionBean
import lego.generate.context.demo_com_mars_lego_test_detail.TestDataBean
import lego.generate.root.init

@LargeTest
@RunWith(AndroidJUnit4::class)
class RouterTestCase {
    companion object{
        const val LOG_TAG = "RouterTest"
    }

    private fun log(msg: String) {
        println("$LOG_TAG $msg ")
    }

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        log(" INIT ")
        Lego.logger {
            d = { msg ->
                log(msg)
            }
            e = { msg, e ->
                log(msg)
                e?.let { throw e }
            }
        }
        Lego.init()
    }

    @After
    fun after(){
        log(" AFTER ")
    }

    @Test
    fun useDetail() {
        // 高阶函数异步返回
        DetailContext.doSthAsyncHOF { s, testDataBean ->
            log("doSthAsyncHOF s:${s} testDataBean:${testDataBean}")
        }


        // 高阶函数
        navigate {
            uri = DetailContext.Uris.DO_STH_HOF
            query {
                "a0" with  4
                "a1" with "123"
                "a2" with TestDataBean(1, "456")
            }
            result<Int> { value ->
                log("DO_STH_HOF value:${value}")
            }
        }

        activityRule.activity.activityPage1(
            TestCompanionBean(5303, "303303"),
            arrayOf(TestCompanionBean(5304, "304304")),
            533,
            ArrayList<String>().apply { add(("i am from b AContext list")) },
            TestDataBean(5203, "203"),
            ArrayList<Int>().apply { add(5123) },
            ArrayList<TestCompanionBean>().apply { add(TestCompanionBean(5305, "305305")) },
            "i am from b AContext",
            Intent.FLAG_ACTIVITY_NEW_TASK
        )

        activityRule.activity.activityPage2KeyStr1KeyStr2KeyStr3(
            "ccc",
            "vvv",
            "bbb",
            Intent.FLAG_ACTIVITY_NEW_TASK,
            102
        )

//        val countdown = CountDownLatch(1)
//        countdown.await()
    }

}

