package com.example.apparchitecture.ui.repos

import com.example.apparchitecture.domain.model.GithubRepoModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface ReposView: MvpView {

    @AddToEndSingle
    fun init()

    @AddToEndSingle
    fun showRepos(repos: List<GithubRepoModel>)

    @AddToEndSingle
    fun stopShimmer()
}