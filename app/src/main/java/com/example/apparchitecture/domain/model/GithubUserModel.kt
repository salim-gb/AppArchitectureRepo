package com.example.apparchitecture.domain.model

import android.os.Parcelable
import com.example.apparchitecture.ui.common.ListItem
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserModel(
    @Expose val id: Long,
    @Expose val login: String,
    @Expose val avatarUrl: String? = null
) : Parcelable, ListItem
