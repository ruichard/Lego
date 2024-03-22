package com.lego.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lego.Lego
import com.lego.logger.Logger
import com.lego.register
import com.lego.router.navigate
import com.lego.router.result
import com.lego.test.router.TestSimpleAggregateFactory
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LegoTest {

    @Test
    fun useLegoLogger() {
        Lego.logger {
            d = { log -> log(log) }
        }
        Logger.d("abc")
    }

    @Test
    fun useRegisterAggregateFactory() {
        Lego.registerAggregateFactory(TestSimpleAggregateFactory())

        navigate {
            uri = "a://b/c"
            result<Int> { result ->
                Assert.assertSame(result, 100)
            }
        }
    }

    @Test
    fun useRegisterAggregateFactoryExt() {
        TestSimpleAggregateFactory().register()

        navigate {
            uri = "a://b/c"
            result<Int> { result ->
                Assert.assertSame(result, 100)
            }
        }
    }

    @Test
    fun useRegisterAggregatable() {
        Lego.registerAggregatable("com.lego.test.router.TestAggregate")

        navigate {
            uri = "a://b/c"
            result<Int> { result ->
                Assert.assertSame(result, 100)
            }
        }
    }
}


fun log(msg: String) {
    println("LegoTest $msg ")
}