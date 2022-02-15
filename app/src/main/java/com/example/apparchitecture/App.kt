package com.example.apparchitecture

import android.app.Application
import com.example.apparchitecture.db.GithubDatabase
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

    val database by lazy {
        GithubDatabase.getDatabase(this)
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }
}