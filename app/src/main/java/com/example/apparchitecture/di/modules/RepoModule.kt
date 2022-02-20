package com.example.apparchitecture.di.modules

import com.example.apparchitecture.db.cache.GithubRepositoriesCache
import com.example.apparchitecture.di.scope.RepoScope
import com.example.apparchitecture.domain.repos.GithubReposRepository
import com.example.apparchitecture.domain.repos.IGithubReposRepository
import com.example.apparchitecture.network.GitHubApiService
import com.example.apparchitecture.network.NetworkStatus
import dagger.Module
import dagger.Provides

@Module
class RepoModule {

    @Provides
    @RepoScope
    fun provideRepoRepository(
        gitHubApiService: GitHubApiService,
        repoCache: GithubRepositoriesCache,
        networkStatus: NetworkStatus
    ): IGithubReposRepository {
        return GithubReposRepository(gitHubApiService, repoCache, networkStatus)
    }
}