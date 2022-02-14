package com.example.apparchitecture.domain.repos

import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.network.GitHubApiService
import io.reactivex.rxjava3.core.Single

class GithubRepoRepository(
    private val gitHubApiService: GitHubApiService
): IGithubReposRepository {

    override fun getRepos(user: GithubUserModel): Single<List<GithubRepoModel>> {
        return gitHubApiService.getRepos(user.reposUrl)
    }
}