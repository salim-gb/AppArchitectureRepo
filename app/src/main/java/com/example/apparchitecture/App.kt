package com.example.apparchitecture

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    val navigatorHolder
        get() = cicerone.getNavigatorHolder()

    val router
        get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }
}