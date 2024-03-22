package com.lego.builder.query

import android.app.Activity
import android.content.Context
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.lego.route.Queries

interface QueriesBuildable {
    fun buildQueries(): Queries
}

interface QueriesFrameable<T> {
    infix fun String.with(value: Any?): T
}

interface BundleQueriesFrameable<T> : QueriesFrameable<T> {
    infix fun String.withValue(value: Any?): T
    infix fun String.withSerializable(value: Any?): T
    infix fun String.withParcelable(value: Parcelable?): T
    infix fun String.withParcelable(value: Array<out Parcelable>?): T
    infix fun String.withParcelable(value: ArrayList<out Parcelable>?): T
}

interface DSLLaunchQueriesFrameable : BundleQueriesFrameable<Unit> {
    var requestCode: Int?
    var flags: Int?
}

interface LinkedLaunchQueriesFrameable<T> : BundleQueriesFrameable<T> {
    fun launchBy(context: Context): T
    fun launchBy(activity: Activity): T
    fun launchBy(fragment: Fragment): T
    fun launchRequestCode(requestCode: Int): T
    fun launchFlags(flags: Int): T
}