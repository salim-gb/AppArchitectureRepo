package com.example.apparchitecture.di.component

import com.example.apparchitecture.di.modules.UserModule
import com.example.apparchitecture.di.scope.UserScope
import com.example.apparchitecture.ui.users.UsersPresenter
import dagger.Subcomponent

@Subcomponent(
    modules = [UserModule::class]
)
@UserScope
interface UserSubcomponent {

    fun repoSubcomponent(): RepoSubcomponent

    fun provideUsersPresenter(): UsersPresenter
}