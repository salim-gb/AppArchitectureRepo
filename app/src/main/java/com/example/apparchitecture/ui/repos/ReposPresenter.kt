package com.example.apparchitecture.ui.repos

import android.util.Log
import com.example.apparchitecture.App
import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.domain.repos.IGithubReposRepository
import com.example.apparchitecture.screens.IScreens
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class ReposPresenter @AssistedInject constructor(
    @Assisted private val userModel: GithubUserModel?,
    private val reposRepository: IGithubReposRepository,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<ReposView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.destroyRepoScope()
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
        router.navigateTo(screens.repoDetailsScreen(repo))
    }

    fun backPressed() {
        router.exit()
    }
}

@AssistedFactory
interface ReposPresenterFactory {

    fun presenter(userModel: GithubUserModel?): ReposPresenter
}