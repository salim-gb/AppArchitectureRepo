package com.example.apparchitecture.domain.model

import android.os.Parcelable
import com.example.apparchitecture.ui.common.ListItem
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepoModel(
    @Expose val name: String,
    @Expose val id: Long,
    @Expose val owner: Owner,
    @Expose val description: String? = null,
    @Expose val forks: Int,
    @Expose val visibility: Boolean,
    @Expose val language: String? = null
) : Parcelable, ListItem
