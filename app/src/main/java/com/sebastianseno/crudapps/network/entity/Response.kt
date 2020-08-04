package com.sebastianseno.crudapps.network.entity

import com.google.gson.annotations.SerializedName

data class Response<out T>(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: T
)

data class ErrorResponse(
    @SerializedName("status") val status: String,
    @SerializedName("message") val message: String,
    @SerializedName("code") val code: Int

)
