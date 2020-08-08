package com.anifichadia.employeehub.service.domain

import java.time.LocalDate

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
data class Employee(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val birthDate: LocalDate,
    val imageThumbnail: Base64EncodedString?,
    val image: Base64EncodedString?
)
