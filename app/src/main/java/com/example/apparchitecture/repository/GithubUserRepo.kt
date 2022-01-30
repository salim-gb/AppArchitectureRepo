package com.example.apparchitecture.repository

import com.example.apparchitecture.R
import com.example.apparchitecture.model.GithubUser

class GithubUserRepo {

    private val randomImg: Int
        get() {
            return when ((0..5).random()) {
                0 -> R.drawable.ava_1
                1 -> R.drawable.ava_2
                2 -> R.drawable.ava_3
                3 -> R.drawable.ava_4
                4 -> R.drawable.ava_5
                else -> R.drawable.ava_6
            }
        }

    private val repositories =
        (0..100).map {
            GithubUser(
                randomImg,
                "login$it"
            )
        }

    fun getUsers(): List<GithubUser> {
        return repositories
    }
}