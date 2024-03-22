package com.lego.builder.router

import android.app.Activity
import android.content.Context
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.lego.builder.query.LinkedLaunchQueriesBuilder

class LinkedLaunchRouterBuilder : BasicRouterBuilder(), LinkedLaunchRouterBuildable {
    override val queriesBuilder = LinkedLaunchQueriesBuilder()
    override fun createUri(): String = uri
    private var uri: String = ""

    // linked
    override fun uri(uri: String) = apply {
        this.uri = uri
    }

    override fun launchBy(context: Context) = apply {
        queriesBuilder.queries.context = context
    }

    override fun launchBy(activity: Activity) = apply {
        queriesBuilder.queries.activity = activity
        queriesBuilder.queries.context = activity.applicationContext
    }

    override fun launchBy(fragment: Fragment) = apply {
        queriesBuilder.queries.fragment = fragment
        queriesBuilder.queries.context = fragment.context
    }

    override fun launchRequestCode(requestCode: Int) = apply {
        queriesBuilder.queries.requestCode = requestCode
    }

    override fun launchFlags(flags: Int) = apply {
        queriesBuilder.queries.flags = flags
    }


    override fun String.withSerializable(value: Any?)= this@LinkedLaunchRouterBuilder.apply {
        queriesBuilder.apply {
            this@withSerializable withSerializable value
        }
    }
    override fun String.withParcelable(value: Parcelable?)= this@LinkedLaunchRouterBuilder.apply {
        queriesBuilder.apply {
            this@withParcelable withParcelable value
        }
    }

    override fun String.withParcelable(value: Array<out Parcelable>?)= this@LinkedLaunchRouterBuilder.apply {
        queriesBuilder.apply {
            this@withParcelable withParcelable value
        }
    }

    override fun String.withParcelable(value: ArrayList<out Parcelable>?)= this@LinkedLaunchRouterBuilder.apply {
        queriesBuilder.apply {
            this@withParcelable withParcelable value
        }
    }

    override fun String.with(value: Any?) = this@LinkedLaunchRouterBuilder.apply {
        queriesBuilder.apply {
            this@with with value
        }
    }

    override fun String.withValue(value: Any?) = this@LinkedLaunchRouterBuilder.apply {
        queriesBuilder.apply {
            this@withValue withValue value
        }
    }

}