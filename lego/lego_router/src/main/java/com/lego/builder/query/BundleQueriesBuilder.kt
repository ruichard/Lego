package com.lego.builder.query

import android.os.Parcelable
import com.lego.route.QueryType

open class BundleQueriesBuilder : BundleQueriesFrameable<Unit>, QueriesBuilder() {
    override fun String.withValue(value: Any?) {
        addQuery(this, value, QueryType.VALUE)
    }

    override infix fun String.withSerializable(value: Any?) {
        addQuery(this, value, QueryType.SERIALIZABLE)
    }

    override infix fun String.withParcelable(value: Parcelable?) {
        addQuery(this, value, QueryType.PARCELABLE)
    }

    override infix fun String.withParcelable(value: Array<out Parcelable>?) {
        addQuery(this, value, QueryType.PARCELABLE)
    }

    override infix fun String.withParcelable(value: ArrayList<out Parcelable>?) {
        addQuery(this, value, QueryType.PARCELABLE)
    }
}
