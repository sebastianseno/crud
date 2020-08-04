package com.sebastianseno.crudapps.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sebastianseno.crudapps.R
import com.sebastianseno.crudapps.extensions.inflate


abstract class BaseRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    open val emptyStateItem: EmptyStateItem? = null

    var data: List<RecyclerItem> = emptyList()
        set(value) {
            val newList = if (value.isEmpty() && emptyStateItem != null) {
                listOf(emptyStateItem!!)
            } else value

            DiffUtil.calculateDiff(DataDiffUtil(field, newList))
                .dispatchUpdatesTo(this)
            field = newList
        }

    var isLoadingMore: Boolean = false
        set(value) {
            if (value) {
                if (data.lastOrNull() != LoadingItem)
                    data = data + LoadingItem
            } else {
                if (data.lastOrNull() == LoadingItem)
                    data = data.dropLastWhile { it is LoadingItem }
            }

            field = value
        }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int = data[position].viewType

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LoadingItem.viewType -> {
                object : RecyclerView.ViewHolder(parent.inflate(R.layout.item_loading)) {}
            }
            EmptyStateItem.VIEW_TYPE -> {
                EmptyViewHolder(parent.inflate(R.layout.layout_empty))
            }
            else -> onCreateItemViewHolder(parent, viewType)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? EmptyViewHolder)?.bind(data[position] as EmptyStateItem)
    }

//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        (holder as? EmptyViewHolder)?.bind(data[position] as EmptyStateItem)
//    }

    abstract fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
}

