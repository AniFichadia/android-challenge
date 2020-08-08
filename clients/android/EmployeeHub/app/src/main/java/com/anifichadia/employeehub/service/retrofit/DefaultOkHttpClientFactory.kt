package com.anifichadia.employeehub.service.retrofit

import android.content.Context
import com.anifichadia.employeehub.service.ServiceConfiguration
import com.anifichadia.employeehub.service.ServiceConfiguration.Companion.tagNetworkLogger
import okhttp3.Cache
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class DefaultOkHttpClientFactory(
    private val context: Context,
    private val serviceConfiguration: ServiceConfiguration
) : OkHttpClientFactory {

    private val interceptors: List<Interceptor> = emptyList()


    override fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        configureInterceptors(builder)

        configureConnectionParameters(builder)

        configureCache(builder)

        return builder.build()
    }

    private fun configureInterceptors(builder: OkHttpClient.Builder) {
        builder.addNetworkInterceptor(
            HttpLoggingInterceptor(
                object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) {
                        Timber.tag(tagNetworkLogger).d(message)
                    }
                }
            ).setLevel(HttpLoggingInterceptor.Level.BODY)
        )

        interceptors.forEach { interceptor -> builder.addInterceptor(interceptor) }
    }

    private fun configureConnectionParameters(builder: OkHttpClient.Builder) {
        // Configure timeouts
        builder.readTimeout(serviceConfiguration.timeoutDuration, serviceConfiguration.timeoutUnits)
            .connectTimeout(serviceConfiguration.timeoutDuration, serviceConfiguration.timeoutUnits)

        // Configure the connection pool. Prevents failures due to the connection pool drying up
        builder.connectionPool(
            ConnectionPool(
                serviceConfiguration.maxConnectionPoolIdleConnections,
                serviceConfiguration.connectionKeepAliveTimeout,
                serviceConfiguration.connectionKeepAliveTimeoutUnits
            )
        )
    }

    private fun configureCache(builder: OkHttpClient.Builder) {
        val cacheDir = File("${context.cacheDir.absolutePath}/NetworkCache")

        val cache = Cache(cacheDir, serviceConfiguration.cacheSizeInBytes)

        builder.cache(cache)
    }
}
