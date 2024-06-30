package com.damilola.remote.interceptors

import com.damilola.config.AppsConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val appsConfig: AppsConfig,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val url = original.url.newBuilder()
            .addQueryParameter("access_key", appsConfig.API_KEY)
            .build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}