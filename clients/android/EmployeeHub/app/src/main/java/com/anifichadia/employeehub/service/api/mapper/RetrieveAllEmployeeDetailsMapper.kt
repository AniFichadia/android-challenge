package com.anifichadia.employeehub.service.api.mapper

import com.anifichadia.employeehub.service.domain.Employee
import com.anifichadia.employeehub.service.retrofit.Mapper
import com.anifichadia.employeehub.service.api.response.Employee as EmployeeDto

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class RetrieveAllEmployeeDetailsMapper : Mapper<List<EmployeeDto>, List<Employee>> {

    /*
     * Since the models are common, just delegate to the single entry retrieval mapper for now.
     * If the domain models were significantly different, this can be removed and this class can be
     * it's own dedicated mapper, but being pragmatic, this is quick and will be used for now
     */
    private val singleEmployeeMapper = RetrieveEmployeeDetailsMapper()


    override fun map(from: List<EmployeeDto>): List<Employee> {
        return from.map(singleEmployeeMapper::map)
    }
}
