package com.example.apparchitecture.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUser(
    val img: Int,
    val login: String
) : Parcelable

