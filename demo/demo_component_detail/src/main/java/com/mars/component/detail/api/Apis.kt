package com.mars.component.detail.api

import android.content.Context
import android.view.View
import com.mars.component.detail.value.TestDataBean
import com.mars.component.detail.value.TestListBean
import com.lego.annotations.route.RRoute

class Apis {
    @RRoute(path = "do-sth")
    fun doSth() {
        println(" AP DBG DETAIL  doSth  begin !!!")
    }

    @RRoute(path = "do-sth-hof", forResult = true, version = "2.0")
    val doSthHOF: (Int, String, TestDataBean) -> Int = { a, b, c ->
        println(" AP DBG DETAIL  doSthHOF begin b:$b c:$c!!!")
        a + 102
    }

    @RRoute(path = "property/property", version = "1.0")
    val property : String = "vbn"


    fun getView(context: Context): View? {
        println(" AP DBG DETAIL  getView begin !!!")
        return View(context)
    }

    fun getSth(id: String, name: String, code1: String, code2: String): String {
        println(" AP DBG DETAIL  getSth begin id:$id name:$name code1:$code1 code2:$code2 !!!")
        return "$id => $name => $code1 => $code2"
    }

    fun getSthNavigationOnly(uri: String): String {
        println(" AP DBG DETAIL  getSthNavigationOnly begin uri:$uri  !!!")
        return "$uri  !"
    }

    fun doSthVararg(no: Int, vararg varargString: String) {
        println("  AP DBG DETAIL  doSthVararg begin strings:${varargString.getOrNull(0)}!!!")
    }

    fun doSthBean(a1: TestDataBean): TestListBean {
        println(" AP DBG DETAIL  doSthBean begin a1:${a1.d1}!!!")
        return TestListBean(55)
    }

}


