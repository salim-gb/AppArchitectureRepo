package com.example.apparchitecture

import com.example.apparchitecture.model.User
import com.example.apparchitecture.ui.UserFragment
import com.example.apparchitecture.ui.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens: IScreens {
    override fun users(): Screen {
        return FragmentScreen { UsersFragment.newInstance() }
    }

    override fun user(user: User): Screen {
        return FragmentScreen { UserFragment.newInstance(user) }
    }
}