package com.example.apparchitecture.ui.details

import com.example.apparchitecture.App
import com.example.apparchitecture.domain.model.GithubRepoModel
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class RepoDetailsPresenter @AssistedInject constructor(
    @Assisted private val githubRepo: GithubRepoModel?,
    private val router: Router
) : MvpPresenter<RepoDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        showRepoDetails()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.destroyRepoDetailsScope()
    }

    private fun showRepoDetails() {
        githubRepo?.let {
            viewState.showDetails(it)
        }
    }

    fun backPressed() {
        router.exit()
    }
}

@AssistedFactory
interface ReposDetailPresenterFactory {

    fun presenter(githubRepo: GithubRepoModel?): RepoDetailsPresenter
}