package com.lego.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lego.router.*
import com.lego.test.router.TestAggregate
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class TouchTest {

    @Before
    fun init(){
        TestAggregate.register()
    }

    @Test
    fun useTouch() {
        touch("test://agg.test") {
            log("touched")
        }
    }

    @Test
    fun useMiss() {
        touch("test://agg.miss") {
            log("touched")
        }.miss {
            log("missed")
        }
    }

    @Test
    fun useRouterTouch() {
        Router.touch("test://agg.test") {
            log("Router touched")
        }
    }

    @Test
    fun useRouterMiss() {
        Router.touch("test://agg.miss") {
            log("Router touched")
        }.miss {
            log("Router missed")
        }
    }

    @Test
    fun useTouchHolder() {
        TouchHolder("test://agg.test").touch{
            log("Holder touched")
        }
    }

    @Test
    fun useTouchHolderMiss() {
        TouchHolder("test://agg.miss").touch{
            log("Holder touched")
        }.miss {
            log("Holder missed")
        }
    }


}
