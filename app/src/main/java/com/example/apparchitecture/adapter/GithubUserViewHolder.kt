package com.example.apparchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.apparchitecture.databinding.ItemUserBinding
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.ui.common.ImageLoader
import com.example.apparchitecture.ui.common.ListItem

class GithubUserViewHolder private constructor(
    private val binding: ItemUserBinding,
    private val imageLoader: ImageLoader<ImageView>,
    onClick: (ListItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var currentGithubUser: GithubUserModel? = null

    init {
        itemView.setOnClickListener {
            currentGithubUser?.let { onClick(it) }
        }
    }

    fun bind(githubUser: GithubUserModel) {
        currentGithubUser = githubUser

        with(binding) {
            githubUser.apply {
                avatarUrl?.let { url ->
                    imageLoader.loadInto(url, userImage)
                }
                tvLogin.text = login
            }
        }
    }

    companion object {

        fun from(
            parent: ViewGroup,
            imageLoader: ImageLoader<ImageView>,
            onClick: (ListItem) -> Unit
        ): GithubUserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
            return GithubUserViewHolder(binding, imageLoader, onClick)
        }
    }
}