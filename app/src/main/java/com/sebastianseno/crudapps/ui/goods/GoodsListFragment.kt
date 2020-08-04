package com.sebastianseno.crudapps.ui.goods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.sebastianseno.crudapps.R
import com.sebastianseno.crudapps.extensions.loadImage
import com.sebastianseno.crudapps.inject.BaseFragment
import com.sebastianseno.crudapps.inject.getViewModel
import com.sebastianseno.crudapps.inject.observe
import com.sebastianseno.crudapps.utils.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_goods_list.*

class GoodsListFragment : BaseFragment() {

    private val viewModel by getViewModel<GoodsViewModel>()

    private val goodsAdapter by lazy(LazyThreadSafetyMode.NONE) {
        GoodsAdapter (::onClick, ::onDelete)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goods_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadMoreListener =
            object : EndlessRecyclerViewScrollListener(recycler.let { it.layoutManager!! }) {
                override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {

                }
            }
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addProductFragment)
        }

        recycler.apply {
            adapter = goodsAdapter
            isNestedScrollingEnabled = true
            addOnScrollListener(loadMoreListener)
        }

        observe(viewModel.allProductSummary) {
            goodsAdapter.data = (it ?: emptyList())
        }

        observe(viewModel.getaBanner) {
            imageBanner?.loadImage(it?.image)
        }
    }

    private fun onClick(goodsId: Int) {
        findNavController().navigate(R.id.detailFragment,
            Bundle().apply { putInt("goodsData", goodsId) }
        )
    }

    private fun onDelete(id: Int) {
        AlertDialog.Builder(requireContext())
            .setTitle("Menghapus Product")
            .setMessage("Apakah Anda yakin untuk menghapus product ini ?")
            .setPositiveButton("Tidak", null)
            .setNegativeButton("Ya") { _, _ -> viewModel.deleteProduct(id)
            }
            .show()
    }
}

