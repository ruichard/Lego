package com.lego.logger

object Logger {
    var bigLogEnable = false
    var d: (String) -> Unit = {}
    var e: (String, Throwable?) -> Unit = { _, _ -> }
    var da: (String, () -> String) -> Unit = { tag, msgAction ->
        if (bigLogEnable){
            d(tag + "|" + msgAction())
        }
    }
    var ea: (String, () -> String, (() -> Throwable)?) -> Unit = { tag, msgAction, ecptAction ->
        e(tag + "|" + if (bigLogEnable){msgAction()} else {" bigLogDisable "}, ecptAction?.let { ecptAction() })
    }
}