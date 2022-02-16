package com.example.apparchitecture.ui.users

import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.domain.users.IGitHubUsersRepository
import com.example.apparchitecture.screens.AppScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    private val router: Router,
    private val usersRepository: IGitHubUsersRepository,
) : MvpPresenter<UsersView>() {

    companion object {
        private const val TAG = "UsersPresenter"
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    private fun loadData() {
        usersRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { users ->
                    viewState.stopShimmer()
                    viewState.updateList(users)
                }, {
                    viewState.showError(it.message)
                }
            )
    }

    fun onUserClicked(githubUser: GithubUserModel) {
        router.navigateTo(AppScreens.reposScreen(githubUser))
    }

    fun backPressed() {
        router.exit()
    }
}