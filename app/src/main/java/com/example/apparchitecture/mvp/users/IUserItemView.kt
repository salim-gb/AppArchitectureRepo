package com.example.apparchitecture.mvp.users

import com.example.apparchitecture.utils.IItemView

interface IUserItemView: IItemView {
    fun setUser(text: String, image: Int)
}