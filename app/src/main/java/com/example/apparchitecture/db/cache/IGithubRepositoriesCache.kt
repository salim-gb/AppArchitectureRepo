package com.example.apparchitecture.db.cache

import com.example.apparchitecture.domain.model.GithubRepoModel
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesCache {

    fun saveCallResult(repos: List<GithubRepoModel>)

    fun getRepos(userId: Long): Single<List<GithubRepoModel>>
}