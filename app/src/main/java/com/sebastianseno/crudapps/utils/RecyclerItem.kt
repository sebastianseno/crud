package com.sebastianseno.crudapps.utils

import androidx.annotation.DrawableRes

interface RecyclerItem {
    val id: Int
    val viewType: Int
}

object LoadingItem : RecyclerItem {
    override val id: Int = 0
    override val viewType: Int = -1
}

data class EmptyStateItem(
    @DrawableRes val imageRes: Int,
    val text: String,
    val lightText: Boolean = false,
    val top: Boolean = false
) : RecyclerItem {

    override val id: Int = 0
    override val viewType: Int = VIEW_TYPE

    companion object {
        const val VIEW_TYPE = -2
    }
}