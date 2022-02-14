package com.example.apparchitecture.ui.users

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.apparchitecture.adapter.GithubUserViewHolder
import com.example.apparchitecture.domain.model.GithubUserModel
import com.example.apparchitecture.ui.common.Delegate
import com.example.apparchitecture.ui.common.ImageLoader
import com.example.apparchitecture.ui.common.ListItem

class UsersDelegate(
    private val imageLoader: ImageLoader<ImageView>
) : Delegate {
    override fun forItem(listItem: ListItem): Boolean = listItem is GithubUserModel

    override fun getViewHolder(
        parent: ViewGroup,
        onClick: (ListItem) -> Unit
    ): RecyclerView.ViewHolder = GithubUserViewHolder.from(parent, imageLoader, onClick)

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder, item: ListItem) {
        (viewHolder as GithubUserViewHolder).let { githubUserViewHolder ->
            val githubUser = item as GithubUserModel
            githubUserViewHolder.bind(githubUser)
        }
    }
}