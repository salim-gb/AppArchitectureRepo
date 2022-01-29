package com.example.apparchitecture.mvp.user

import com.example.apparchitecture.repository.GithubUserRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserPresenter(
    val userRepo: GithubUserRepo,
    val router: Router,
    val userPosition: Int?
) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        getUser()
    }

    private fun getUser() {
        val users = userRepo.getUsers()
        userPosition?.let {
            val user = users[userPosition]
            viewState.displayUser(user)
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
