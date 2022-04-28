package com.example.simplecryptolisting

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var mInstance: MyApplication
        fun getContext(): Context {
            return mInstance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }

}