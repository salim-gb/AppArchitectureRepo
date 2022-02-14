package com.example.apparchitecture.network

import com.example.apparchitecture.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GitHubApiService {

    @GET("/users")
    fun getUsers(): Single<List<GithubUserModel>>
}