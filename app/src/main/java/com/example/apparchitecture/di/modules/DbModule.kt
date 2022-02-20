package com.example.apparchitecture.di.modules

import android.content.Context
import com.example.apparchitecture.db.GithubDatabase
import com.example.apparchitecture.db.cache.GithubRepositoriesCache
import com.example.apparchitecture.db.cache.GithubUsersCache
import com.example.apparchitecture.db.cache.IGithubRepositoriesCache
import com.example.apparchitecture.db.cache.IGithubUsersCache
import com.example.apparchitecture.db.dao.ReposDao
import com.example.apparchitecture.db.dao.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun db(context: Context): GithubDatabase {
        return GithubDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun userDao(db: GithubDatabase): UserDao {
        return db.userDao
    }

    @Provides
    @Singleton
    fun repoDao(db: GithubDatabase): ReposDao {
        return db.reposDao
    }

    @Provides
    @Singleton
    fun usersCache(userDao: UserDao): IGithubUsersCache {
        return GithubUsersCache(userDao)
    }

    @Provides
    @Singleton
    fun reposCache(repoDao: ReposDao): IGithubRepositoriesCache {
        return GithubRepositoriesCache(repoDao)
    }
}