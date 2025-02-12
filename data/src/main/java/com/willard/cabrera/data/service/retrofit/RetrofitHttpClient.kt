package com.willard.cabrera.data.service.retrofit

import com.willard.cabrera.data.BuildConfig
import com.willard.cabrera.data.service.base.HttpClientProvider
import com.willard.cabrera.data.service.base.HttpInterceptorProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHttpClient(
    baseUrl: String,
    private val interceptors: List<HttpInterceptorProvider>,
) : HttpClientProvider {

    private val okHttpClient: OkHttpClient by lazy {
        val builder = OkHttpClient.Builder()

        interceptors.forEach { interceptor ->
            builder.addInterceptor(interceptor.provideInterceptor())
        }

        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            builder.addInterceptor(loggingInterceptor)
        }

        builder.build()
    }

    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    override suspend fun get(url: String): Any? {
        return apiService.get(url).body()
    }

    override suspend fun post(url: String, body: Any): Any? {
        return apiService.post(url, body).body()
    }

    override suspend fun put(url: String, body: Any): Any? {
        return apiService.put(url, body).body()
    }

    override suspend fun delete(url: String): Any? {
        return apiService.delete(url).body()
    }
}