package com.example.apparchitecture.db.cache

import com.example.apparchitecture.db.dao.ReposDao
import com.example.apparchitecture.db.entity.GithubRepoEntity
import com.example.apparchitecture.domain.model.GithubRepoModel
import io.reactivex.rxjava3.core.Single

class GithubRepositoriesCache(
    private val repoDao: ReposDao
) : IGithubRepositoriesCache {

    override fun saveCallResult(repos: List<GithubRepoModel>) {
        repoDao.insert(repos.map { GithubRepoEntity.fromGithubRepoModel(it) })
    }

    override fun getRepos(userId: Long): Single<List<GithubRepoModel>> = repoDao.getAll(userId)
        .map { repos ->
            repos.map { it.toGithubRepoModel() }
        }
}