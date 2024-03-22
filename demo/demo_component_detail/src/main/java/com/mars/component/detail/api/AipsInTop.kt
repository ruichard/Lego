package com.mars.component.detail.api

import com.mars.component.detail.value.TestDataBean
import com.lego.annotations.route.RRoute


@RRoute(path = "doSthTop")
fun doSthTop(
    ints: Array<Int>,
    li: List<Int?>,
    strings: Array<String>,
    ls: List<String>,
    beans: Array<TestDataBean>,
    lb: List<TestDataBean>
): List<TestDataBean> {
    println(" AP DBG DETAIL  doSthTop begin beans:${ints[0]} li:${li[0]} ls:${ls[0]} strings:${strings[0]} beans:${beans[0]} lb:${lb[0]} !!!")
    return lb
}

@RRoute(path = "doSthHOFTop")
val doSthHOFTop: (Unit) -> Unit = {
    println(" AP DBG DETAIL  doSthHOFTop begin !!!")
}

@RRoute(path = "property/top")
var propertyTop: String = "bnm"
