package com.example.apparchitecture.ui.repos

import android.util.Log
import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.domain.repos.IGithubReposRepository
import com.example.apparchitecture.screens.AppScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ReposPresenter(
    private val userModel: GithubUserModel?,
    private val reposRepository: IGithubReposRepository,
    private val router: Router
) : MvpPresenter<ReposView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()
    }

    private fun loadData() {
        userModel?.let {
            reposRepository.getRepos(it)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { repos ->
                        viewState.showRepos(repos)
                        viewState.stopShimmer()
                    }, {
                        Log.e("Repos", "Error getting repos", it)
                    }
                )
        }
    }

    fun onRepoClick(repo: GithubRepoModel) {
        router.navigateTo(AppScreens.repoDetailsScreen(repo))
    }

    fun backPressed(): Boolean {
        return true
    }
}