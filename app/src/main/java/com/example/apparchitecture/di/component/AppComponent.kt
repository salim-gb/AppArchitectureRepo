package com.example.apparchitecture.di.component

import com.example.apparchitecture.di.modules.ContextModule
import com.example.apparchitecture.di.modules.DbModule
import com.example.apparchitecture.di.modules.NavigationModule
import com.example.apparchitecture.di.modules.NetworkModule
import com.example.apparchitecture.ui.main.MainActivity
import com.example.apparchitecture.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ContextModule::class,
        NavigationModule::class,
        NetworkModule::class,
        DbModule::class
    ]
)
@Singleton
interface AppComponent {

    fun userSubcomponent(): UserSubcomponent

    fun provideMainPresenter(): MainPresenter

//    fun provideReposDetailsPresenterFactory(): ReposDetailPresenterFactory

    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)

//    fun inject(detailsPresenter: RepoDetailsPresenter)
}