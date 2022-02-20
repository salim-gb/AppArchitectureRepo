package com.example.apparchitecture

import android.app.Application
import com.example.apparchitecture.di.component.DaggerAppComponent
import com.example.apparchitecture.di.component.RepoDetailsSubcomponent
import com.example.apparchitecture.di.component.RepoSubcomponent
import com.example.apparchitecture.di.component.UserSubcomponent
import com.example.apparchitecture.di.modules.ContextModule

class App : Application() {

    val appComponent by lazy {
        DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repoSubcomponent: RepoSubcomponent? = null
        private set

    var repoDetailsSubcomponent: RepoDetailsSubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    fun initUserSubcomponent(): UserSubcomponent {
        userSubcomponent = appComponent.userSubcomponent()
        return userSubcomponent!!
    }

    fun initRepoSubcomponent(): RepoSubcomponent {
        repoSubcomponent = appComponent.userSubcomponent().repoSubcomponent()
        return repoSubcomponent!!
    }

    fun initRepoDetailsSubcomponent(): RepoDetailsSubcomponent {
        repoDetailsSubcomponent =
            appComponent.userSubcomponent().repoSubcomponent().repoDetailsSubcomponent()
        return repoDetailsSubcomponent!!
    }

    fun destroyUserScope() {
        userSubcomponent = null
    }

    fun destroyRepoScope() {
        repoSubcomponent = null
    }

    fun destroyRepoDetailsScope() {
        repoDetailsSubcomponent = null
    }
    companion object {

        private var _instance: App? = null
        val instance
            get() = _instance!!
    }
}