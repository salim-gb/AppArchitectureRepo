package com.example.apparchitecture.ui.users

import com.example.apparchitecture.domain.model.GithubUserModel
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    fun init()
    fun updateList(users: List<GithubUserModel>)
    fun showError(message: String?)
    fun stopShimmer()
}