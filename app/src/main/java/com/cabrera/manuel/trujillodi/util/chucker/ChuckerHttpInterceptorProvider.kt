package com.cabrera.manuel.trujillodi.util.chucker

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.willard.cabrera.data.service.base.HttpInterceptorProvider
import okhttp3.Interceptor

class ChuckerHttpInterceptorProvider(private val context: Context): HttpInterceptorProvider {
    override fun provideInterceptor(): Interceptor {
        return ChuckerInterceptor.Builder(context).build()
    }
}