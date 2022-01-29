package com.example.apparchitecture

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(position: Int): Screen
}

