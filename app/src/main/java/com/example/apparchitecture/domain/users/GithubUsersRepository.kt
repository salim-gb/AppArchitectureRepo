package com.example.apparchitecture.domain.users

import com.example.apparchitecture.db.cache.IGithubUsersCache
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.network.GitHubApiService
import com.example.apparchitecture.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubUsersRepository(
    private val gitHubApiService: GitHubApiService,
    private val usersCache: IGithubUsersCache,
    private val networkStatus: NetworkStatus
) : IGitHubUsersRepository {

    override fun getUsers(): Single<List<GithubUserModel>> = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                gitHubApiService.getUsers().flatMap { users ->
                    usersCache.saveCallResult(users)
                    Single.just(users)
                }
            } else {
                usersCache.getUsers()
            }
        }
}