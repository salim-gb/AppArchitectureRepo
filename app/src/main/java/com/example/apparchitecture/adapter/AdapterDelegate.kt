package com.example.apparchitecture.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.apparchitecture.ui.common.CommonDiffUtilCallback
import com.example.apparchitecture.ui.common.Delegate
import com.example.apparchitecture.ui.common.ListItem

class AdapterDelegate(
    private val delegates: List<Delegate>,
    private val onClick: (ListItem) -> Unit
) : ListAdapter<ListItem, RecyclerView.ViewHolder>(CommonDiffUtilCallback<ListItem>()) {

    override fun getItemViewType(position: Int) =
        delegates.indexOfFirst { delegate -> delegate.forItem(currentList[position]) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegates[viewType].getViewHolder(parent, onClick)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegates[getItemViewType(position)].bindViewHolder(holder, currentList[position])
    }

    override fun getItemCount(): Int = currentList.size
}