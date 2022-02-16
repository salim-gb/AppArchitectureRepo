package com.example.apparchitecture.ui.common

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}