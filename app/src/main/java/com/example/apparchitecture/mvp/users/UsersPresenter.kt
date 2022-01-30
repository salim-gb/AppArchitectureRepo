package com.example.apparchitecture.mvp.users

import android.util.Log
import com.example.apparchitecture.IScreens
import com.example.apparchitecture.model.GithubUser
import com.example.apparchitecture.model.User
import com.example.apparchitecture.repository.GithubUserRepo
import com.example.apparchitecture.utils.Utils
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class UsersPresenter(
    val userRepo: GithubUserRepo,
    val router: Router,
    val screens: IScreens
) : MvpPresenter<UsersView>() {

    companion object {
        private const val TAG = "UsersPresenter"
    }

    class UserListPresenter : IUserListPresenter {

        val users = mutableListOf<User>()

        override var itemClickListener: ((IUserItemView) -> Unit)? = null

        override fun bindView(view: IUserItemView) {
            val user = users[view.pos]
            view.setUser(user)
        }

        override fun getCount() = users.size
    }

    val usersListPresenter = UserListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            router.navigateTo(
                screens.user(usersListPresenter.users[itemView.pos])
            )
        }
    }

    private fun loadData() {
        getObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { githubUsers ->
                return@map Utils.convertGithubUsersToUsers(githubUsers)
            }
            .subscribe(getObserver())
    }

    private fun getObservable(): Observable<List<GithubUser>> {
        return Observable.create { e ->
            if (!e.isDisposed) {
                e.onNext(userRepo.getUsers())
                e.onComplete()
            }
        }
    }

    private fun getObserver(): Observer<List<User>> {
        return object : Observer<List<User>> {
            override fun onSubscribe(d: Disposable) {
                Log.d(TAG, "onSubscribe: ${d.isDisposed}")
            }

            override fun onNext(usersList: List<User>) {
                usersListPresenter.users.addAll(usersList)
                viewState.updateList()
            }

            override fun onError(e: Throwable) {
                Log.d(TAG, "onError : ${e.message}")
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
        }
    }

    fun backPressed(): Boolean {
        return true
    }
}