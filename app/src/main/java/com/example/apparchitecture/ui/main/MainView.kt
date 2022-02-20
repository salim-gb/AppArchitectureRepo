package com.example.apparchitecture.ui.main

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface MainView : MvpView {

    @AddToEndSingle
    fun showNoInternetMessage()

    @AddToEndSingle
    fun hideNoInternetMessage()
}
