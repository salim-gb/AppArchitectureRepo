package com.example.apparchitecture.domain.users

import com.example.apparchitecture.db.dao.UserDao
import com.example.apparchitecture.db.entity.GithubUserEntity
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.network.GitHubApiService
import com.example.apparchitecture.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubUsersRepository(
    private val gitHubApiService: GitHubApiService,
    private val userDao: UserDao,
    private val networkStatus: NetworkStatus
) : IGitHubUsersRepository {

    override fun getUsers(): Single<List<GithubUserModel>> = networkStatus.isOnlineSingle()
        .flatMap { isOnline ->
            if (isOnline) {
                gitHubApiService.getUsers()
                    .flatMap { users ->
                        //TODO extract cache user to room
                        userDao.insert(
                            users.map {
                                GithubUserEntity(it.id, it.login, it.avatarUrl ?: "", it.reposUrl)
                            }
                        )
                        Single.just(users)
                    }
            } else {
                //TODO extract get users from room
                userDao.getAll()
                    .map { users ->
                        users.map {
                                user ->
                            GithubUserModel(user.id, user.login, user.avatarUrl, user.reposUrl)
                        }
                    }
            }
        }
}