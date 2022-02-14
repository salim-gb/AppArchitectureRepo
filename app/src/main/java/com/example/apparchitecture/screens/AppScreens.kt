package com.example.apparchitecture.screens

import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.ui.details.RepoDetailsFragment
import com.example.apparchitecture.ui.repos.ReposFragment
import com.example.apparchitecture.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AppScreens {
    fun usersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }

    fun reposScreen(user: GithubUserModel) = FragmentScreen {
        ReposFragment.newInstance(user)
    }

    fun repoDetailsScreen(repo: GithubRepoModel) = FragmentScreen {
        RepoDetailsFragment.newInstance(repo)
    }
}