package com.sebastianseno.crudapps.ui.main

import android.util.Log
import com.sebastianseno.crudapps.db.repo.ProductRepo
import com.sebastianseno.crudapps.network.service.GoodsServices
import com.sebastianseno.crudapps.utils.BaseViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val productRepo: ProductRepo
) : BaseViewModel() {

     fun getGoods() {
        launch {
            try {
                productRepo.getAllProducts()
                Log.i("getGoods", "Berhasil")
            } catch (error: Exception) {
                Log.e("getGoods", error?.localizedMessage)

            }
        }
    }
}