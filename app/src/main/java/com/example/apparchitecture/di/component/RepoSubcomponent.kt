package com.example.apparchitecture.di.component

import com.example.apparchitecture.di.modules.RepoModule
import com.example.apparchitecture.di.scope.RepoScope
import com.example.apparchitecture.ui.repos.ReposPresenterFactory
import dagger.Subcomponent

@Subcomponent(
    modules = [RepoModule::class]
)
@RepoScope
interface RepoSubcomponent {

    fun repoDetailsSubcomponent(): RepoDetailsSubcomponent

    fun provideRepoPresenterFactory(): ReposPresenterFactory
}