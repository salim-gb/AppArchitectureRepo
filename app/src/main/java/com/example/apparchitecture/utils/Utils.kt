package com.example.apparchitecture.utils

import com.example.apparchitecture.model.GithubUser
import com.example.apparchitecture.model.User

object Utils {

    fun convertGithubUsersToUsers(githubUsers: List<GithubUser>): List<User> {
        val userList = ArrayList<User>()
        var isAuthorized = false
        for (githubUser in githubUsers) {
            isAuthorized = !isAuthorized
            val user = User(githubUser.img, githubUser.login, isAuthorized)
            userList.add(user)
        }

        return userList
    }
}