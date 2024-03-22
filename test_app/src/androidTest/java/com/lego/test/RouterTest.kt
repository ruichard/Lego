package com.lego.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lego.router.navigate
import com.lego.router.navigateForResult
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
class RouterTest {
    @Before
    fun init(){
        TestAggregate.register()
    }

    @Test
    fun useBadUri() {
        val result = navigateForResult<Int> {
            uri = "a://b/c"
            query {
                "p1" with 1
            }
        }
        Assert.assertSame(result, 2)
    }

    @Test
    fun useRouterAdd1() {
        navigate {
            uri = "test://agg.test/int/add1"
            query {
                "p1" with 1
            }
            result<Int> { result ->
                Assert.assertSame(result, 2)
            }
        }
    }

    @Test
    fun useRouterAdd2() {
        navigate {
            uri = "test://agg.test/int/add2"
            query {
                "p1" with 1
            }
            result<Int> { result ->
                Assert.assertSame(result, 3)
            }
        }
    }

    @Test
    fun useRouterAdd1ForResult() {
        val result = navigateForResult<Int> {
            uri = "test://agg.test/int/add1"
            query {
                "p1" with 1
            }
        }
        Assert.assertSame(result, 2)
    }

    @Test
    fun useRouterAdd2ForResult() {
        val result = navigateForResult<Int> {
            uri = "test://agg.test/int/add2"
            query {
                "p1" with 1
            }
        }
        Assert.assertSame(result, 3)
    }
}