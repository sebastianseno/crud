package com.sebastianseno.crudapps.network

import android.util.Log
import com.sebastianseno.crudapps.network.entity.ErrorResponse
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Retrofit
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.security.cert.CertificateException

class ResponseInterceptor: Interceptor {

    var retrofit: Retrofit? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = try {
            chain.proceed(request)
        } catch (error: Exception) {
            Log.d("error","exception Result : ",error)
            val message = when (error) {
                is UnknownHostException -> "Tidak dapat terhubung dengan server"
                is SocketTimeoutException -> "Koneksi timeout"
                is CertificateException -> "Membutuhkan sambungan koneksi terpercaya"
                else -> "Something went wrong"
            }
            throw IOException(message)
        }
        throwIfError(response)

        return response
    }

    private fun throwIfError(response: Response) {
        if (response.code() in 200..299) {
            return
        }
        val errorBody = getError<ErrorResponse>(response.body())

        if (errorBody != null) {
            val message = errorBody.message
            val exception = IOException(message)
            Log.w("exception","error Body",exception)
            throw exception
        }
    }

    private inline fun <reified T> getError(body: ResponseBody?): T? = body?.let {
        retrofit?.responseBodyConverter<T>(T::class.java, T::class.java.annotations)
            ?.convert(it)
    }
}