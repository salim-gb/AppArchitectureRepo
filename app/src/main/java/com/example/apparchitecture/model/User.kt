package com.example.apparchitecture.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val img: Int,
    val login: String,
    val isAuthorized: Boolean = false
): Parcelable