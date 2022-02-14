package com.example.apparchitecture.ui.details

import com.example.apparchitecture.domain.model.GithubRepoModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface RepoDetailsView : MvpView {

    @AddToEndSingle
    fun showDetails(repo: GithubRepoModel)
}