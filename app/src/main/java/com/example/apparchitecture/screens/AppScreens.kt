package com.example.apparchitecture.screens

import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.ui.details.RepoDetailsFragment
import com.example.apparchitecture.ui.repos.ReposFragment
import com.example.apparchitecture.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface IScreens {
    fun usersScreen(): FragmentScreen
    fun reposScreen(user: GithubUserModel): FragmentScreen
    fun repoDetailsScreen(repo: GithubRepoModel): FragmentScreen
}

class AppScreens : IScreens {
    override fun usersScreen() = FragmentScreen {
        UsersFragment.newInstance()
    }

    override fun reposScreen(user: GithubUserModel) = FragmentScreen {
        ReposFragment.newInstance(user)
    }

    override fun repoDetailsScreen(repo: GithubRepoModel) = FragmentScreen {
        RepoDetailsFragment.newInstance(repo)
    }
}