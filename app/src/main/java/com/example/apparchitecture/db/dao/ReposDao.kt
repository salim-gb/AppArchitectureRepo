package com.example.apparchitecture.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.apparchitecture.db.entity.GithubRepoEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface ReposDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: List<GithubRepoEntity>)

    @Query("SELECT * FROM GithubRepoEntity WHERE userId = :userId")
    fun getAll(userId: Long): Single<List<GithubRepoEntity>>
}