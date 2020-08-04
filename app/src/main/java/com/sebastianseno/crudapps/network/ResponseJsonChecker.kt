package com.sebastianseno.crudapps.network

import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

object ResponseJsonChecker : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return Converter<ResponseBody, Any> {
            val delegate = retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
            try {
                delegate.convert(it)
            } catch (error: Exception) {
                Log.e("Shakespeare quotes ", error.message)
                throw IOException("Terjadi kesalahan pada server")
            }
        }
    }
}