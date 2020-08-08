package com.anifichadia.employeehub.service.retrofit

import retrofit2.Retrofit

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
interface RetrofitFactory {
    fun createRetrofit(): Retrofit
}
