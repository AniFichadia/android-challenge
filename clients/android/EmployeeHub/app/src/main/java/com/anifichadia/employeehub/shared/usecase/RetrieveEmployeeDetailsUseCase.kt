package com.anifichadia.employeehub.shared.usecase

import com.anifichadia.employeehub.framework.UseCase
import com.anifichadia.employeehub.service.domain.Employee

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-09
 */
interface RetrieveEmployeeDetailsUseCase : UseCase<Int, RetrieveEmployeeDetailsUseCase.Result> {

    sealed class Result {
        class Success(
            val employee: Employee
        ) : Result()

        object Failure : Result()
    }
}
