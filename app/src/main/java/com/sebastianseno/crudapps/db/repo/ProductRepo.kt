package com.sebastianseno.crudapps.db.repo

import androidx.lifecycle.LiveData
import com.sebastianseno.crudapps.db.QasirDb
import com.sebastianseno.crudapps.db.dao.BannerDao
import com.sebastianseno.crudapps.db.dao.ProductDao
import com.sebastianseno.crudapps.db.entities.BannerDb
import com.sebastianseno.crudapps.db.entities.ProductDb
import com.sebastianseno.crudapps.db.entities.ProductSummary
import com.sebastianseno.crudapps.db.toDb
import com.sebastianseno.crudapps.network.service.GoodsServices

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepo @Inject constructor(
    private val goodsServices: GoodsServices,
    qasirDb: QasirDb
) {

    private val productDao: ProductDao = qasirDb.productDao()

    private val goodsDao: BannerDao = qasirDb.bannerDao()

    fun getBanner(): LiveData<BannerDb> {
        return goodsDao.getBannerImage()
    }

    fun getAllProductSummaries() : LiveData<List<ProductSummary>>{
        return productDao.getProductsSummaries()
    }

    fun getProductbyId(id : Int) : LiveData<ProductDb> {
        return productDao.getProductById(id)
    }

    suspend fun getAllProducts() = withContext(Dispatchers.IO) {
        val response = goodsServices.getQasirGoods().await()
        val products = response.data.products?.map { it.toDb() }
        val banner = response.data.banner!!.toDb()
        productDao.insert(products)
        goodsDao.insert(banner)
    }

    suspend fun deleteProduct(id: Int) = withContext(Dispatchers.IO) {
        productDao.deleteProduct(id)
    }

    suspend fun addProductsOffline(
        namaProducts: String,
        hargaProducts: Int,
        stokProducts: Int,
        diskripsiProducts: String,
        photo: String
    ) = withContext(Dispatchers.IO) {
        val productDB = ProductDb(
            0,namaProducts, hargaProducts, stokProducts,diskripsiProducts, photo, ""
        )
        productDao.insert(productDB)

    }
}