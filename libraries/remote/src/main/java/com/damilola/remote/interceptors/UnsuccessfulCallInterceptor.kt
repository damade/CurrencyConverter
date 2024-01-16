package com.damilola.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

internal object UnsuccessfulCallInterceptor: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val mainResponse: Response = chain.proceed(chain.request())
        try {
            if (mainResponse.code !in 200..226) {
                val errorMessage = JSONObject(mainResponse.body?.string() ?: "").getString("message")
                throw IOException(mainResponse.message + ": " + errorMessage )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
        return mainResponse
    }
}