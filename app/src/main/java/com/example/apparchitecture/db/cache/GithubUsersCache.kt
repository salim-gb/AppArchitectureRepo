package com.example.apparchitecture.db.cache

import com.example.apparchitecture.db.dao.UserDao
import com.example.apparchitecture.db.entity.GithubUserEntity
import com.example.apparchitecture.domain.model.GithubUserModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GithubUsersCache @Inject constructor(
    private val userDao: UserDao
) : IGithubUsersCache {

    override fun saveCallResult(users: List<GithubUserModel>) {

        userDao.insert(users.map {
            GithubUserEntity.fromGithubUserModel(it)
        })
    }

    override fun getUsers(): Single<List<GithubUserModel>> = userDao.getAll()
        .map { users ->
            users.map { it.toGithubUserModel() }
        }
}