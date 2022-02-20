package com.example.apparchitecture.di.modules

import com.example.apparchitecture.db.cache.GithubUsersCache
import com.example.apparchitecture.di.scope.UserScope
import com.example.apparchitecture.domain.users.GithubUsersRepository
import com.example.apparchitecture.domain.users.IGithubUsersRepository
import com.example.apparchitecture.network.GitHubApiService
import com.example.apparchitecture.network.NetworkStatus
import dagger.Module
import dagger.Provides

@Module
class UserModule {

    @Provides
    @UserScope
    fun provideUserRepository(
        gitHubApiService: GitHubApiService,
        usersCache: GithubUsersCache,
        networkStatus: NetworkStatus
    ): IGithubUsersRepository {
        return GithubUsersRepository(gitHubApiService, usersCache, networkStatus)
    }
}
