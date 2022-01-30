package com.example.apparchitecture.mvp.users

import com.example.apparchitecture.model.User
import com.example.apparchitecture.utils.IItemView

interface IUserItemView: IItemView {
    fun setUser(user: User)
}