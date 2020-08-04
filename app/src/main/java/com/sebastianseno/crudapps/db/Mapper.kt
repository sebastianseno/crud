package com.sebastianseno.crudapps.db

import com.sebastianseno.crudapps.db.entities.BannerDb
import com.sebastianseno.crudapps.db.entities.ProductDb
import com.sebastianseno.crudapps.network.entity.*

fun ProductsGson.toDb(): ProductDb {
    return ProductDb(
      productId, productName, price, stock, description, imagesProducts?.thumbnail.orEmpty(),
        imagesProducts?.large.orEmpty()
    )
}

fun BannerGson.toDb(): BannerDb {
    return BannerDb(
        1, image
    )
}