package com.example.apparchitecture.domain.repos

import com.example.apparchitecture.db.dao.ReposDao
import com.example.apparchitecture.db.entity.GithubRepoEntity
import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.domain.model.Owner
import com.example.apparchitecture.network.GitHubApiService
import com.example.apparchitecture.network.NetworkStatus
import io.reactivex.rxjava3.core.Single

class GithubReposRepository(
    private val gitHubApiService: GitHubApiService,
    private val repoDao: ReposDao,
    private val networkStatus: NetworkStatus
) : IGithubReposRepository {

    override fun getRepos(user: GithubUserModel): Single<List<GithubRepoModel>> =
        networkStatus.isOnlineSingle()
            .flatMap { isOnline ->
                if (isOnline) {
                    gitHubApiService.getRepos(user.reposUrl)
                        .flatMap { repos ->
                            //TODO extract cache repos to room
                            repoDao.insert(repos.map {
                                GithubRepoEntity(
                                    it.id,
                                    it.name,
                                    it.owner.ownerId,
                                    it.owner.login,
                                    it.owner.avatarUrl,
                                    it.description,
                                    it.forks,
                                    it.visibility,
                                    it.language
                                )
                            })
                            Single.just(repos)
                        }
                } else {
                    //TODO extract get repos from room
                    repoDao.getAll(user.id)
                        .map { repos ->
                            repos.map { repo ->
                                GithubRepoModel(
                                    repo.name,
                                    repo.id,
                                    Owner(repo.userId, repo.login, repo.avatarUrl),
                                    repo.description,
                                    repo.forks,
                                    repo.visibility,
                                    repo.language
                                )
                            }
                        }
                }
            }
}