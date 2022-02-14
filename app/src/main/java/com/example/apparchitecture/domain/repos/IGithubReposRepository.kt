package com.example.apparchitecture.domain.repos

import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface IGithubReposRepository {

    fun getRepos(user: GithubUserModel): Single<List<GithubRepoModel>>
}