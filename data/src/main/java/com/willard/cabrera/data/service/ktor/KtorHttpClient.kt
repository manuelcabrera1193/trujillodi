package com.willard.cabrera.data.service.ktor

import com.willard.cabrera.data.BuildConfig
import com.willard.cabrera.data.service.base.HttpClientProvider
import com.willard.cabrera.data.service.base.HttpInterceptorProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class KtorHttpClient(
    private val baseUrl: String,
    private val interceptors: List<HttpInterceptorProvider>,
) : HttpClientProvider {

    private fun String.generateUrl() : String {
        return "$baseUrl$this"
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    private val okHttpClient = OkHttpClient.Builder()
        .apply {
            interceptors.forEach { interceptor ->
                addInterceptor(interceptor.provideInterceptor())
            }
        }
        .addInterceptor(loggingInterceptor).build()

    private val client = HttpClient(OkHttp) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.BODY
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15000
        }
        install(HttpRequestRetry) {
            maxRetries = 3
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
        engine {
            preconfigured = okHttpClient
        }
    }

    override suspend fun get(url: String): Any {
        return client.get(url.generateUrl()).bodyAsText()
    }

    override suspend fun post(url: String, body: Any): Any {
        return client.post(url.generateUrl()) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.bodyAsText()
    }

    override suspend fun put(url: String, body: Any): Any {
        return client.put(url.generateUrl()) {
            contentType(ContentType.Application.Json)
            setBody(body)
        }.bodyAsText()
    }

    override suspend fun delete(url: String): Any {
        return client.delete(url.generateUrl()).body()
    }
}
