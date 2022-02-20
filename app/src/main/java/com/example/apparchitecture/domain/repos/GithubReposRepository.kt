package com.example.apparchitecture.domain.repos

import com.example.apparchitecture.db.cache.IGithubRepositoriesCache
import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.network.GitHubApiService
import com.example.apparchitecture.network.NetworkStatus
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubReposRepository  @Inject constructor(
    private val gitHubApiService: GitHubApiService,
    private val reposCache: IGithubRepositoriesCache,
    private val networkStatus: NetworkStatus
) : IGithubReposRepository {

    override fun getRepos(user: GithubUserModel): Single<List<GithubRepoModel>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                gitHubApiService.getRepos(user.reposUrl).flatMap { repos ->
                    reposCache.saveCallResult(repos)
                    Single.just(repos)
                }
            } else {
                reposCache.getRepos(user.id)
            }
        }
}