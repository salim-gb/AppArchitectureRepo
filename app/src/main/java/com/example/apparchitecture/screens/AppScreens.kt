package com.example.apparchitecture.screens

import com.example.apparchitecture.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AppScreens {
    fun usersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }
}