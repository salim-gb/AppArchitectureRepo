package com.example.apparchitecture.db.cache

import com.example.apparchitecture.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface IGithubUsersCache {

    fun saveCallResult(users: List<GithubUserModel>)

    fun getUsers(): Single<List<GithubUserModel>>
}