package com.example.apparchitecture.mvp.main

import com.example.apparchitecture.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screen: IScreens) : MvpPresenter<MainView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screen.users())
    }

    fun backClicked() {
        router.exit()
    }
}
