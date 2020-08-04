package com.sebastianseno.crudapps.network.entity

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class GoodsDataGson(
    @SerializedName("banner") val banner: BannerGson?,
    @SerializedName("products") val products: List<ProductsGson>?
)

data class BannerGson(
    @SerializedName("image")
    @Expose val image: String
)

data class ProductsGson(
    @SerializedName("product_id") val productId: Int,
    @SerializedName("product_name") val productName: String,
    @SerializedName("price") val price: Int,
    @SerializedName("stock") val stock: Int,
    @SerializedName("description") val description: String,
    @SerializedName("images") val imagesProducts: ImagesGson?
)

data class ImagesGson(
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("large") val large: String

)
