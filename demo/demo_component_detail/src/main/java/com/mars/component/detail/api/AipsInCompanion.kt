package com.mars.component.detail.api

import com.lego.annotations.route.RRoute

class AipsInCompanion {
    companion object DetailCompanion {
        @RRoute(path = "doSthCompanion")
        fun doSthCompanion(i: Int, j: String, k: Long): String {
            println(" AP DBG DETAIL  doSthCompanion begin !!! i:$i j:$j k:$k")
            return "result = i:$i j:$j k:$k"
        }

        @RRoute(path = "property/companion")
        var propertyCompanion: String = "bnm3"

        @RRoute(path = "doSthHOFCompanion")
        val doSthHOFCompanion: (Int) -> Int = {
            println(" AP DBG DETAIL  doSthHOFCompanion begin !!!")
            it + 102
        }
    }
}
