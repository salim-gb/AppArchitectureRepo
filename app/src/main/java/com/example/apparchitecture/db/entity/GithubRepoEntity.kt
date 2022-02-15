package com.example.apparchitecture.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = GithubUserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GithubRepoEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val userId: Long,
    val login: String,
    val avatarUrl: String? = null,
    val description: String? = null,
    val forks: Int,
    val visibility: Boolean,
    val language: String? = null
)