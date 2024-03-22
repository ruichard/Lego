package com.lego.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lego.router.Router
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
class RouterBuilderTest {
    @Before
    fun init(){
        TestAggregate.register()
    }

    @Test
    fun useBadUri() {
        val result = Router.builder()
            .uri("a://b/c")
            .apply {
                "p1" with 1
            }
            .build().routeForResult(Integer.TYPE)
        Assert.assertSame(result, 2)
    }

    @Test
    fun useRouterAdd1() {
        Router.builder()
            .uri("test://agg.test/int/add1")
            .apply {
                "p1" with 1
            }.result<Int> { result ->
                Assert.assertSame(result, 2)
            }
            .build().route()
    }

    @Test
    fun useRouterAdd2() {
        Router.builder()
            .uri("test://agg.test/int/add2")
            .apply {
                "p1" with 1
            }.result<Int> { result ->
                Assert.assertSame(result, 3)
            }
            .build().route()
    }

    @Test
    fun useRouterAdd1ForResult() {
        val result = Router.builder()
            .uri("test://agg.test/int/add1")
            .apply {
                "p1" with 1
            }
            .build().routeForResult(Integer.TYPE)
        Assert.assertSame(result, 2)

    }

    @Test
    fun useRouterAdd2ForResult() {
        val result = Router.builder()
            .uri("test://agg.test/int/add2")
            .apply {
                "p1" with 1
            }
            .build().routeForResult(Integer.TYPE)
        Assert.assertSame(result, 3)
    }
}