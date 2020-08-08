package com.anifichadia.employeehub.shared

import com.anifichadia.employeehub.service.ServiceConfiguration

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
data class AppConfiguration(
    val debug: Boolean = true,
    val serviceConfiguration: ServiceConfiguration = ServiceConfiguration(
        baseUrl = "http://localhost:5000/"
    )
)
