package com.lego.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lego.router.property

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        log(" TestActivity onCreate from:${property<String>("from")}" )
    }

    private fun log(msg: String) {
        println("LegoTest $msg ")
    }
}
