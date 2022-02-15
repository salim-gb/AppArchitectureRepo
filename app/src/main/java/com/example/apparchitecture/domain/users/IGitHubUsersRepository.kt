package com.example.apparchitecture.domain.users

import com.example.apparchitecture.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single

interface IGitHubUsersRepository {

    fun getUsers(): Single<List<GithubUserModel>>
}