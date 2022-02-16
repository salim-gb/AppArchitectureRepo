package com.example.apparchitecture.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.ui.common.Extensions.toMD5

@Entity
data class GithubUserEntity(
    @PrimaryKey
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val localAvatarUrl: String = "${avatarUrl.toMD5()}.jpg",
    val reposUrl: String
) {
    fun toGithubUserModel() = GithubUserModel(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        reposUrl = reposUrl
    )

    companion object {
        fun fromGithubUserModel(user: GithubUserModel) = GithubUserEntity(
            id = user.id,
            login = user.login,
            avatarUrl = user.avatarUrl ?: "",
            reposUrl = user.reposUrl
        )
    }
}
