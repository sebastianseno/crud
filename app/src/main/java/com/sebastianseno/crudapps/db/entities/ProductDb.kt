package com.sebastianseno.crudapps.db.entities

import android.net.Uri
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.sebastianseno.crudapps.utils.RecyclerItem

@Entity
data class ProductDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nameProduct: String,
    val priceProduct: Int,
    val stockProduct: Int,
    val descriptionProduct: String,
    val thumbnail: String,
    val largeImage: String
) {
    override fun toString(): String = nameProduct
}

data class ProductSummary(
    override val id: Int,
    val nameProduct: String,
    val priceProduct: Int,
    val stockProduct: Int,
    val descriptionProduct: String,
    val thumbnail: String,
    val largeImage: String
) : RecyclerItem {

    @Ignore
    override val viewType: Int = 0
}

@Entity
data class BannerDb(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val image: String
)
