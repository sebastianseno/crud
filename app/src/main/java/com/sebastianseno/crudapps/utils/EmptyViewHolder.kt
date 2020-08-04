package com.sebastianseno.crudapps.utils

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.layout_empty.*


class EmptyViewHolder(override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer{


    fun bind(item: EmptyStateItem) {
        emptyImage.setImageResource(item.imageRes)
        emptyText.text = item.text
        if (item.lightText)
            emptyText.setTextColor(Color.WHITE)
    }
}