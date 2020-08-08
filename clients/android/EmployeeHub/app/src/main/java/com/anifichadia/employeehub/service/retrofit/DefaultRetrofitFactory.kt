package com.anifichadia.employeehub.service.retrofit

import com.anifichadia.employeehub.service.ServiceConfiguration
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class DefaultRetrofitFactory(
    private val serviceConfiguration: ServiceConfiguration,
    private val okHttpClientFactory: OkHttpClientFactory
) : RetrofitFactory {

    override fun createRetrofit(): Retrofit {
        val builder = Retrofit.Builder()
            .baseUrl(serviceConfiguration.baseUrl)
            .client(okHttpClientFactory.createOkHttpClient())

        configureConverterFactory(builder)

        return builder.build()
    }

    private fun configureConverterFactory(builder: Retrofit.Builder) {
        builder.addConverterFactory(GsonConverterFactory.create())
    }
}
