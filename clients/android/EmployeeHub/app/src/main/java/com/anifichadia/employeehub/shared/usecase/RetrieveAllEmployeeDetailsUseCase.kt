package com.anifichadia.employeehub.shared.usecase

import com.anifichadia.employeehub.framework.UseCase
import com.anifichadia.employeehub.service.domain.Employee

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
interface RetrieveAllEmployeeDetailsUseCase : UseCase<Unit, RetrieveAllEmployeeDetailsUseCase.Result> {

    sealed class Result {
        class Success(
            val employees: List<Employee>
        ) : Result()

        object Failure : Result()
    }
}
