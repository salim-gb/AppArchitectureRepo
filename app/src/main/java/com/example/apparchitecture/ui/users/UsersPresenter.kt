package com.example.apparchitecture.ui.users

import com.example.apparchitecture.App
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.domain.users.IGithubUsersRepository
import com.example.apparchitecture.screens.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter @Inject constructor(
    private val router: Router,
    private val usersRepository: IGithubUsersRepository,
    private val screens: IScreens
) : MvpPresenter<UsersView>() {

    companion object {
        private const val TAG = "UsersPresenter"
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        App.instance.destroyUserScope()
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
        router.navigateTo(screens.reposScreen(githubUser))
    }

    fun backPressed() {
        router.exit()
    }
}