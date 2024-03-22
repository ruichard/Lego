package com.lego.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lego.context.LifeCycleEvent
import com.lego.router.*
import com.lego.test.router.TestAggregate
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class EventTest {

    @get:Rule
    var activityRule = ActivityTestRule(TestActivity::class.java)

    @Before
    fun init(){
        TestAggregate.register()
    }

    @Test
    fun useInitEvent() {
        Router.doEvent(LifeCycleEvent.INIT)
    }

    @Test
    fun useDestroyEvent(){
        Router.doEvent(LifeCycleEvent.DESTROY)

    }

    @Test
    fun useContextInitEvent() {
        activityRule.activity.applicationContext.doEvent("context_init")
    }

    @Test
    fun useContextDestroyEvent(){
        activityRule.activity.applicationContext.doEvent("context_destroy")
    }


}