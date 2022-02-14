package com.example.apparchitecture.domain.users

import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.network.GitHubApiService
import io.reactivex.rxjava3.core.Single

class GithubUsersRepository(
    private val gitHubApiService: GitHubApiService,
) : IGitHubUsersRepository {

    override fun getUsers(): Single<List<GithubUserModel>> {
        return gitHubApiService.getUsers()
    }
}