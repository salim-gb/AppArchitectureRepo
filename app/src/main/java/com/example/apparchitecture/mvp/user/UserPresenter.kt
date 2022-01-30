package com.example.apparchitecture.mvp.user

import com.example.apparchitecture.model.User
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    val router: Router,
    val user: User?
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getUser()
    }

    private fun getUser() {
        user?.let {
            viewState.displayUser(it)
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
