package com.reza.commbank.commBank.util

import com.reza.commbank.commBank.util.API
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by reza on 30/11/17.
 */
class ApiInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {

        val original = chain?.request()
        val originalHttpUrl = original?.url()

        val url = originalHttpUrl?.newBuilder()?.
                addQueryParameter("api_key", API.KEY.value)?.
                build()

        val requestBuilder = original?.newBuilder()?.url(url)
        val  request = requestBuilder?.build()

        return chain?.proceed(request)
    }
}