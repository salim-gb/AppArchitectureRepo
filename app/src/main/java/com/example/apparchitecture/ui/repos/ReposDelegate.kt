package com.example.apparchitecture.ui.repos

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apparchitecture.adapter.GithubRepoViewHolder
import com.example.apparchitecture.domain.model.GithubRepoModel
import com.example.apparchitecture.ui.common.Delegate
import com.example.apparchitecture.ui.common.ListItem

class ReposDelegate : Delegate {

    override fun forItem(listItem: ListItem): Boolean = listItem is GithubRepoModel

    override fun getViewHolder(
        parent: ViewGroup,
        onClick: (ListItem) -> Unit
    ): RecyclerView.ViewHolder = GithubRepoViewHolder.from(parent, onClick)

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ListItem) {
        (viewHolder as GithubRepoViewHolder).let { githubRepoViewHolder ->
            val githubRepo = item as GithubRepoModel
            githubRepoViewHolder.bind(githubRepo)
        }
    }
}