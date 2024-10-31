package com.example.testsrcdir

import android.app.Application
import com.example.someProperty

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        println(someProperty)
    }
}