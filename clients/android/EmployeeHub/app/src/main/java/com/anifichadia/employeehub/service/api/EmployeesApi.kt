package com.anifichadia.employeehub.service.api

import com.anifichadia.employeehub.service.api.response.Employee
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
interface EmployeesApi {
    @GET("/employees")
    fun retrieveAllEmployeeDetails(): Call<List<Employee>>

    @GET("/employees/{id}")
    fun retrieveEmployeeDetails(@Path("id") id: Int): Call<Employee>
}
