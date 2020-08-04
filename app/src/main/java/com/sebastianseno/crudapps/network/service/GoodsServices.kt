package com.sebastianseno.crudapps.network.service

import com.sebastianseno.crudapps.network.entity.*
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GoodsServices {

    @GET ("bins/gsq5w")
    fun getQasirGoods() : Deferred<Response<GoodsDataGson>>
}