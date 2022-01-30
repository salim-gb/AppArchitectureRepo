package com.example.apparchitecture

import com.example.apparchitecture.model.User
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: User): Screen
}

