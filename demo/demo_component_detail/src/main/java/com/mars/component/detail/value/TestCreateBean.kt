package com.mars.component.detail.value

import com.google.gson.annotations.SerializedName
import com.lego.annotations.route.RRoute
import com.lego.annotations.route.RValue

@RValue
data class TestCreateBean @RRoute(
    path = "test-bean/create"
) constructor(
    @SerializedName("data1")
    val d1: Int?,
    @SerializedName("data2")
    val d2: String?
)
