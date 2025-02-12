package com.willard.cabrera.data.service.base

interface HttpClientProvider {
    suspend fun get(url: String): Any?
    suspend fun post(url: String, body: Any): Any?
    suspend fun put(url: String, body: Any): Any?
    suspend fun delete(url: String): Any?
}
