package com.example.apparchitecture

import android.app.Application
import com.example.apparchitecture.di.component.DaggerAppComponent
import com.example.apparchitecture.di.modules.ContextModule

class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }
}