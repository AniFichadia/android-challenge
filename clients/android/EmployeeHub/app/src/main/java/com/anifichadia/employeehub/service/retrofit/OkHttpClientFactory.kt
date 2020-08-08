package com.anifichadia.employeehub.service.retrofit

import okhttp3.OkHttpClient

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
interface OkHttpClientFactory {
    fun createOkHttpClient(): OkHttpClient
}
