package com.example.apparchitecture.di.component

import com.example.apparchitecture.di.modules.*
import com.example.apparchitecture.ui.details.RepoDetailsPresenter
import com.example.apparchitecture.ui.details.ReposDetailPresenterFactory
import com.example.apparchitecture.ui.main.MainActivity
import com.example.apparchitecture.ui.main.MainPresenter
import com.example.apparchitecture.ui.repos.ReposPresenter
import com.example.apparchitecture.ui.repos.ReposPresenterFactory
import com.example.apparchitecture.ui.users.UsersPresenter
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ContextModule::class,
        NavigationModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DbModule::class
    ]
)
@Singleton
interface AppComponent {

    fun provideMainPresenter(): MainPresenter

    fun provideUsersPresenter(): UsersPresenter

    fun provideReposPresenterFactory(): ReposPresenterFactory

    fun provideReposDetailsPresenterFactory(): ReposDetailPresenterFactory

    fun inject(mainActivity: MainActivity)
    
    fun inject(mainPresenter: MainPresenter)

    fun inject(usersPresenter: UsersPresenter)

    fun inject(reposPresenter: ReposPresenter)

    fun inject(detailsPresenter: RepoDetailsPresenter)
}