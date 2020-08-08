package com.anifichadia.employeehub.service.api.mapper

import com.anifichadia.employeehub.service.domain.Base64EncodedString
import com.anifichadia.employeehub.service.domain.Employee
import com.anifichadia.employeehub.service.retrofit.Mapper
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import com.anifichadia.employeehub.service.api.response.Employee as EmployeeDto

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class RetrieveEmployeeDetailsMapper : Mapper<EmployeeDto, Employee> {

    private val dateFormat = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH)


    override fun map(from: EmployeeDto): Employee {
        return Employee(
            id = from.id,
            firstName = from.firstName,
            lastName = from.lastName,
            gender = from.gender,
            birthDate = LocalDate.parse(from.birthDate, dateFormat),
            imageThumbnail = from.thumbImage?.let(::Base64EncodedString),
            image = from.image?.let(::Base64EncodedString)
        )
    }
}
