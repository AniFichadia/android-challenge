package com.anifichadia.employeehub.service

import com.anifichadia.employeehub.service.domain.Employee

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
interface EmployeeHubService {
    fun retrieveAllEmployeeDetails(): ApiResult<List<Employee>>

    fun retrieveEmployeeDetails(employeeId: Int): ApiResult<Employee>
}
