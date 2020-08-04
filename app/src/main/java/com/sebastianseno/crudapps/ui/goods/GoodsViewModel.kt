package com.sebastianseno.crudapps.ui.goods

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.sebastianseno.crudapps.common.ActionLiveData
import com.sebastianseno.crudapps.common.UiState
import com.sebastianseno.crudapps.db.entities.BannerDb
import com.sebastianseno.crudapps.db.entities.ProductDb
import com.sebastianseno.crudapps.db.entities.ProductSummary
import com.sebastianseno.crudapps.db.repo.ProductRepo
import com.sebastianseno.crudapps.utils.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.Exception

class GoodsViewModel @Inject constructor(
    private val productRepo: ProductRepo

) : BaseViewModel() {

    private val productId = MutableLiveData<Int>()

    val product: LiveData<ProductDb> = Transformations.switchMap(productId) {
        productRepo.getProductbyId(it)
    }

    val allProductSummary: LiveData<List<ProductSummary>>
        get() = productRepo.getAllProductSummaries()

    val getaBanner: LiveData<BannerDb>
        get() = productRepo.getBanner()

    val state = ActionLiveData<UiState>()

    fun getproductById(id: Int) {
        if (id != productId.value) {
            productId.value = id
            launch {
                try {
                    productRepo.getProductbyId(id)
                } catch (error: Exception) {

                }
            }
        }
    }

    fun inpuitProducts(
        namaProducts: String,
        hargaProducts: Int?,
        stokProducts: Int?,
        diskripsiProducts: String,
        photo: String?
    ) {
        val error = when {
            namaProducts.isEmpty() -> "Nama Product"
            hargaProducts == null -> "Harga Product"
            stokProducts == null -> "Stok Product"
            diskripsiProducts.isEmpty() -> "Diskripsi Product"
            else -> null
        }
        if (error != null) {
            state.sendAction(UiState.Error("$error Wajib Diisi"))
            return
        }
        state.sendAction(UiState.Loading)
        viewModelScope.launch {
            try {
                productRepo.addProductsOffline(
                    namaProducts, hargaProducts?: 0, stokProducts ?: 0, diskripsiProducts, photo ?: ""
                )
                state.sendAction(UiState.Success)
            } catch (error: Exception) {
                state.sendAction(UiState.Error(error.message!!))
            }
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            try {
                productRepo.deleteProduct(id)
            } catch (error: Exception) {

            }
        }
    }
}