package com.example.apparchitecture.ui.details

import com.example.apparchitecture.domain.model.GithubRepoModel
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class RepoDetailsPresenter(
    private val githubRepo: GithubRepoModel?,
    private val router: Router
) : MvpPresenter<RepoDetailsView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        showRepoDetails()
    }

    private fun showRepoDetails() {
        githubRepo?.let {
            viewState.showDetails(it)

        }
    }

    fun backPressed(): Boolean {
        return true
    }
}