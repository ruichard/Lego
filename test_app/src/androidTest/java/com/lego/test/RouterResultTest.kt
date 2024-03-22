package com.lego.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lego.router.navigate
import com.lego.router.result
import com.lego.test.router.TestAggregate
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class RouterResultTest {
    @Before
    fun init(){
        TestAggregate.register()
    }

    @Test
    fun useRouterResult3() {
        navigate {
            uri = "test://agg.test/result/get3"
            result<String, String, String> { r1, r2, r3 ->
                Assert.assertSame(r1, "r1")
                Assert.assertSame(r2, "r2")
                Assert.assertSame(r3, "r3")
            }
        }
    }

    @Test
    fun useRouterResult4() {
        navigate {
            uri = "test://agg.test/result/get4"
            result<String, String, String, String> { r1, r2, r3, r4->
                Assert.assertSame(r1, "r1")
                Assert.assertSame(r2, "r2")
                Assert.assertSame(r3, "r3")
                Assert.assertSame(r4, "r4")
            }
        }
    }

    @Test
    fun useRouterResult5() {
        navigate {
            uri = "test://agg.test/result/get5"
            result<String, String, String, String, String> { r1, r2, r3, r4, r5 ->
                Assert.assertSame(r1, "r1")
                Assert.assertSame(r2, "r2")
                Assert.assertSame(r3, "r3")
                Assert.assertSame(r4, "r4")
                Assert.assertSame(r5, "r5")
            }
        }
    }
}