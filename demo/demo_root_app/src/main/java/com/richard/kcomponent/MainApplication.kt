package com.richard.kcomponent

import android.app.Application
import android.util.Log
import com.lego.Lego
import lego.generate.root.init

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Lego.logger {
            bigLogEnable = false
            d = { log -> Log.d("R DEBUG", log) }
            e = { log, e ->
                Log.e("R ERROR", "$log ")
                e?.let {
                    throw it
                }
            }
        }
        Lego.init()
    }
}