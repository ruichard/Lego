package com.lego.test

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.lego.activity.Launcher
import com.lego.route.LaunchQueries
import com.lego.route.Query
import com.lego.router.navigate
import com.lego.test.router.TestAggregate
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
class LaunchTest {

    @get:Rule
    var activityRule = ActivityTestRule(TestActivity::class.java)

    @Before
    fun init(){
        TestAggregate.register()
    }

    @Test
    fun useLaunchQueries() {
        activityRule.activity.navigate {
            uri = "test://agg.test/activity/launch"
            query {
                "p1" with 1
            }
        }
    }

    @Test
    fun useLaunch(){
        Launcher().launch(
            TestActivity::class.java,
            LaunchQueries().apply {
                context = activityRule.activity.applicationContext
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            },
            null,
            null
        )
    }

    @Test
    fun useLaunchQuery(){
        Launcher().launch(
            TestActivity::class.java,
            LaunchQueries().apply {
                context = activityRule.activity.applicationContext
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                add(Query("from", "useLaunchQuery"))
            },
            null,
            null
        )
    }

    @Test
    fun useLaunchPathQuery(){
        Launcher().launch(
            TestActivity::class.java,
            LaunchQueries().apply {
                context = activityRule.activity.applicationContext
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            },
            listOf(Query("from", "useLaunchPathQuery")),
            null
        )
    }
}