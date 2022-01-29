package com.example.apparchitecture.mvp.users

import com.example.apparchitecture.*
import com.example.apparchitecture.model.GithubUser
import com.example.apparchitecture.repository.GithubUserRepo
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UsersPresenter(
    val userRepo: GithubUserRepo,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<UsersView>() {

    class UserListPresenter : IUserListPresenter {

        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setUser(user.login, user.img)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(screens.user(itemView.pos))
        }
    }

    fun loadData() {
        val users = userRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        return true
    }
}