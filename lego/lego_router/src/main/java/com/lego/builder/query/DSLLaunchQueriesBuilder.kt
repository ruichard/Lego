package com.lego.builder.query

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment
import com.lego.route.LaunchQueries

class DSLLaunchQueriesBuilder(context: Context?) :BundleQueriesBuilder(), DSLLaunchQueriesFrameable {
    override val queries: LaunchQueries = LaunchQueries()

    init {
        queries.context = context
    }

    constructor(activity: Activity) : this(activity.applicationContext) {
        queries.activity = activity
    }

    constructor(fragment: Fragment) : this(fragment.context) {
        queries.fragment = fragment
    }

    override var flags: Int? = null
        set(value) {
            queries.flags = value
            field = value
        }

    override var requestCode: Int? = null
        set(value) {
            queries.requestCode = value
            field = value
        }


}
