package com.sebastianseno.crudapps.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.sebastianseno.crudapps.db.entities.BannerDb
import com.sebastianseno.crudapps.db.entities.ProductDb
import com.sebastianseno.crudapps.db.entities.ProductSummary

@Dao
abstract class ProductDao : BaseDao<ProductDb> {

    @Query("SELECT * FROM ProductDb WHERE id")
    abstract fun getProduct(): LiveData<List<ProductDb>>

    @Transaction
    @Query("SELECT * FROM ProductDb")
    abstract fun getProductsSummaries(): LiveData<List<ProductSummary>>

    @Query("SELECT * FROM ProductDb WHERE id = :id")
    abstract fun getProductById(id: Int): LiveData<ProductDb>

    @Query("DELETE FROM ProductDb WHERE id = :id")
    abstract fun deleteProduct(id: Int)
}

@Dao
abstract class BannerDao : BaseDao<BannerDb> {
    @Query("SELECT * FROM BannerDb")
    abstract fun getBannerImage(): LiveData<BannerDb>
}