package com.example.apparchitecture.ui.main

import com.example.apparchitecture.App
import com.example.apparchitecture.screens.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        App.instance.appComponent.inject(this)

        router.replaceScreen(screens.usersScreen())
    }

    fun showNoInternetMessage() {
        viewState.showNoInternetMessage()
    }

    fun hideNoInternetMessage() {
        viewState.hideNoInternetMessage()
    }
}
