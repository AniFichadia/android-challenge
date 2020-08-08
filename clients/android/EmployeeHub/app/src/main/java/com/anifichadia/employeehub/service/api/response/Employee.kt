package com.anifichadia.employeehub.service.api.response

import com.google.gson.annotations.SerializedName

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
data class Employee(
    @SerializedName("id") val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("birth_date") val birthDate: String,
    @SerializedName("thumbImage") val thumbImage: String?,
    @SerializedName("image") val image: String?
)
