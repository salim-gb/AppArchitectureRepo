package com.example.apparchitecture.ui.users

import com.example.apparchitecture.domain.model.GithubUserModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface UsersView : MvpView {

    @AddToEndSingle
    fun init()

    @AddToEndSingle
    fun updateList(users: List<GithubUserModel>)

    @Skip
    fun showError(message: String?)

    @AddToEndSingle
    fun stopShimmer()
}