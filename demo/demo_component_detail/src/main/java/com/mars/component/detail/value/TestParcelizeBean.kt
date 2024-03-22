package com.mars.component.detail.value

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.lego.annotations.route.RValue
import kotlinx.android.parcel.Parcelize

@RValue
@Parcelize
data class TestParcelizeBean(
    val d1: Int?,
    val d2: String?
) : Parcelable