package com.anifichadia.employeehub.service

import com.anifichadia.employeehub.service.api.EmployeesApi
import com.anifichadia.employeehub.service.api.mapper.RetrieveAllEmployeeDetailsMapper
import com.anifichadia.employeehub.service.api.mapper.RetrieveEmployeeDetailsMapper
import com.anifichadia.employeehub.service.domain.Employee
import com.anifichadia.employeehub.service.retrofit.RetrofitCallWrapper
import retrofit2.Retrofit

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class EmployeeHubServiceImpl(
    private val retrofit: Retrofit
) : EmployeeHubService {

    private val employeesApi: EmployeesApi by lazy { retrofit.create(EmployeesApi::class.java) }


    override fun retrieveAllEmployeeDetails(): ApiResult<List<Employee>> {
        return RetrofitCallWrapper(RetrieveAllEmployeeDetailsMapper())
            .execute(employeesApi.retrieveAllEmployeeDetails())
    }

    override fun retrieveEmployeeDetails(employeeId: Int): ApiResult<Employee> {
        return RetrofitCallWrapper(RetrieveEmployeeDetailsMapper())
            .execute(employeesApi.retrieveEmployeeDetails(employeeId))
    }
}
