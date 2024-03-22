package com.mars.component.detail.value

import com.lego.annotations.route.RValue

@RValue
data class TestCompanionBean(val d1: Int, val d2: String)  {
    companion object {
        const val C1 = 33
        const val C2 = "CONST_COMP"
    }
}
