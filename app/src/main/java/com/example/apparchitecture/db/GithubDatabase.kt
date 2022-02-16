package com.example.apparchitecture.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.apparchitecture.db.dao.ReposDao
import com.example.apparchitecture.db.dao.UserDao
import com.example.apparchitecture.db.entity.GithubRepoEntity
import com.example.apparchitecture.db.entity.GithubUserEntity

@Database(
    entities = [GithubUserEntity::class, GithubRepoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {

    abstract val userDao: UserDao
    abstract val reposDao: ReposDao

    companion object {

        @Volatile
        private var INSTANCE: GithubDatabase? = null

        fun getDatabase(context: Context): GithubDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GithubDatabase::class.java,
                    "github_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}