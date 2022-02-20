package com.example.apparchitecture.di.component

import com.example.apparchitecture.di.modules.RepoDetailsModule
import com.example.apparchitecture.ui.details.ReposDetailPresenterFactory
import dagger.Subcomponent

@Subcomponent(
    modules = [RepoDetailsModule::class]
)
interface RepoDetailsSubcomponent {

    fun provideRepoDetailPresenterFactory(): ReposDetailPresenterFactory
}