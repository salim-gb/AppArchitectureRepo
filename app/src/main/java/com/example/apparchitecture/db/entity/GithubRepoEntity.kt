package com.example.apparchitecture.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.domain.model.Owner

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
) {

    fun toGithubRepoModel() = GithubRepoModel(
        id = id,
        name = name,
        owner = Owner(userId, login, avatarUrl),
        description = description,
        forks = forks,
        visibility = visibility,
        language = language
    )

    companion object {
        fun fromGithubRepoModel(repo: GithubRepoModel) = GithubRepoEntity(
            id = repo.id,
            name = repo.name,
            userId = repo.owner.ownerId,
            login = repo.owner.login,
            avatarUrl = repo.owner.avatarUrl,
            description = repo.description,
            forks = repo.forks,
            visibility = repo.visibility,
            language = repo.language
        )
    }
}