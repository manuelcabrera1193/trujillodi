package com.willard.cabrera.data.service.base

import com.willard.cabrera.data.service.ktor.KtorHttpClient
import com.willard.cabrera.data.service.retrofit.RetrofitHttpClient

class HttpClientFactory(
    private val baseUrl: String,
    private val interceptors: List<HttpInterceptorProvider>,
) {
    fun create(type: HttpClientType): HttpClientProvider {
        return when (type) {
            HttpClientType.KTOR -> KtorHttpClient(
                baseUrl = baseUrl,
                interceptors = interceptors,
            )

            HttpClientType.RETROFIT -> RetrofitHttpClient(
                baseUrl = baseUrl,
                interceptors = interceptors,
            )
        }
    }
}