package com.example.apparchitecture.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apparchitecture.databinding.ItemRepoBinding
import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.ui.common.ListItem

class GithubRepoViewHolder private constructor(
    private val binding: ItemRepoBinding,
    onClick: (ListItem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var currentRepo: GithubRepoModel? = null

    init {
        itemView.setOnClickListener {
            currentRepo?.let { onClick(it) }
        }
    }

    fun bind(githubRepo: GithubRepoModel) {
        currentRepo = githubRepo

        githubRepo.apply {
            binding.tvRepo.text = name
        }
    }

    companion object {

        fun from(
            parent: ViewGroup,
            onClick: (ListItem) -> Unit
        ): GithubRepoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemRepoBinding.inflate(layoutInflater, parent, false)
            return GithubRepoViewHolder(binding, onClick)
        }
    }
}