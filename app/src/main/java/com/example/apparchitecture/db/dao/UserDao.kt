package com.example.apparchitecture.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apparchitecture.db.entity.GithubUserEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: List<GithubUserEntity>)

    @Query("SELECT * FROM GithubUserEntity")
    fun getAll(): Single<List<GithubUserEntity>>
}