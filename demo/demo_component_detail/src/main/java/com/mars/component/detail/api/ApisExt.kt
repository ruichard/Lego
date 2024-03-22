package com.mars.component.detail.api

import com.lego.annotations.route.RExtend
import com.lego.annotations.route.RRoute

@RRoute(path = "do-sth-ext")
fun doSthExt(s: String, @RExtend i: Int): Int {
    println(" AP DBG DETAIL  doSthExt i:$i !!!")
    return i
}