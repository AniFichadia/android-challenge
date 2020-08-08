package com.anifichadia.employeehub.service

import java.util.concurrent.TimeUnit

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
data class ServiceConfiguration(
    val baseUrl: String,

    val timeoutDuration: Long = 15,
    val timeoutUnits: TimeUnit = TimeUnit.SECONDS,

    val maxConnectionPoolIdleConnections: Int = 20,
    val connectionKeepAliveTimeout: Long = 30,
    val connectionKeepAliveTimeoutUnits: TimeUnit = TimeUnit.SECONDS,

    val cacheSizeInBytes: Long = 32 * 1024 * 1024 // 32 MB
) {

    companion object {
        const val tagNetworkLogger = "NetworkLogger"
    }
}
