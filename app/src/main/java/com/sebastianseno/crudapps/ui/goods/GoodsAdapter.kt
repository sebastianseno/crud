package com.sebastianseno.crudapps.ui.goods

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sebastianseno.crudapps.R
import com.sebastianseno.crudapps.db.entities.ProductSummary
import com.sebastianseno.crudapps.extensions.inflate
import com.sebastianseno.crudapps.extensions.loadUri
import com.sebastianseno.crudapps.extensions.toRupiah
import com.sebastianseno.crudapps.utils.BaseRecyclerAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_goods.*

class GoodsAdapter(
    private val onClick: (Int) -> Unit,
    private val onDelete: (Int) -> Unit
) : BaseRecyclerAdapter() {

    override fun onCreateItemViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_goods))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        (holder as? ViewHolder)?.bind(data[position] as ProductSummary)
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener {
                onClick((data[adapterPosition] as ProductSummary).id)
            }
            deleteButton.setOnClickListener {
                onDelete((data[adapterPosition] as ProductSummary).id)

            }
            productNameText.maxLines = 2
        }

        fun bind(productDb: ProductSummary) {
            if (productDb.thumbnail.isNotEmpty()) {
                thumbnailImage.loadUri(productDb.thumbnail)
            }
            val name = productDb.nameProduct
            productNameText.text = name
            productNameText.text = productDb.nameProduct
            priceText.text = productDb.priceProduct.toLong().toRupiah()
            stockText.text = productDb.stockProduct.toString()
        }
    }

}

