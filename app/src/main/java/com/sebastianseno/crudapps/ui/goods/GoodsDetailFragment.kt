package com.sebastianseno.crudapps.ui.goods

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat

import com.sebastianseno.crudapps.R
import com.sebastianseno.crudapps.extensions.loadImage
import com.sebastianseno.crudapps.extensions.loadUri
import com.sebastianseno.crudapps.extensions.toRupiah
import com.sebastianseno.crudapps.extensions.visibleIf
import com.sebastianseno.crudapps.inject.BaseFragment
import com.sebastianseno.crudapps.inject.getViewModel
import com.sebastianseno.crudapps.inject.observe
import kotlinx.android.synthetic.main.fragment_goods_detail.*
import kotlinx.android.synthetic.main.fragment_goods_detail.priceText
import kotlinx.android.synthetic.main.fragment_goods_detail.productNameText
import kotlinx.android.synthetic.main.item_goods.*

class GoodsDetailFragment : BaseFragment() {

    private val viewModel by getViewModel<GoodsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val argument = arguments?.getInt("goodsData") ?: 0
        viewModel.getproductById(argument)

        emptyLayout.visibleIf(argument == 0)
        cardLayout.visibleIf(argument != 0)

        observe(viewModel.product) {
            if (it != null) {
                if (!it.thumbnail.isBlank()) {
                    largeImage.loadUri(it.thumbnail)
                }
                productNameText.text = it.nameProduct
                activity?.title = it.nameProduct
                descriptionText?.text = HtmlCompat.fromHtml(
                    it.descriptionProduct,
                    HtmlCompat.FROM_HTML_MODE_COMPACT
                )
                priceText.text = "${it.priceProduct.toLong().toRupiah()} / pcs"
            }
        }
    }
}
