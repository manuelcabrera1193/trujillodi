package com.willard.cabrera.data.service.base

import okhttp3.Interceptor

interface HttpInterceptorProvider {
    fun provideInterceptor(): Interceptor
}