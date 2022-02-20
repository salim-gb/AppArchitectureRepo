package com.example.apparchitecture.di.modules

import com.example.apparchitecture.domain.repos.GithubReposRepository
import com.example.apparchitecture.domain.repos.IGithubReposRepository
import com.example.apparchitecture.domain.users.GithubUsersRepository
import com.example.apparchitecture.domain.users.IGitHubUsersRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun provideUserRepository(impl: GithubUsersRepository): IGitHubUsersRepository

    @Binds
    @Singleton
    fun provideRepoRepository(impl: GithubReposRepository): IGithubReposRepository
}