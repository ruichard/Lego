package com.lego.builder.query

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.lego.route.LaunchQueries

class LinkedLaunchQueriesBuilder :BundleQueriesBuilder(), LinkedLaunchQueriesFrameable<Unit> {
    override val queries: LaunchQueries = LaunchQueries()

    override fun launchBy(context: Context) {
        queries.context = context
    }

    override fun launchBy(activity: Activity) {
        queries.activity = activity
        queries.context = activity.applicationContext
    }

    override fun launchBy(fragment: Fragment) {
        queries.fragment = fragment
        queries.context = fragment.context
    }

    override fun launchRequestCode(requestCode: Int) {
        queries.requestCode = requestCode
    }

    override fun launchFlags(flags: Int) {
        queries.flags = flags
    }

}
