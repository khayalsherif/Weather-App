package com.example.weatherapp.data.network

import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val headers = HashMap<String, String>()
        headers["Accept"] = "application/json"

        return chain.proceed(request.headers(headers.toHeaders()).build())
    }
}