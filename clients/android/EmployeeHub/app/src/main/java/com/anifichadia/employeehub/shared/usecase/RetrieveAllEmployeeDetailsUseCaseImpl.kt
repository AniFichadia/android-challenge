package com.anifichadia.employeehub.shared.usecase

import com.anifichadia.employeehub.service.ApiResult
import com.anifichadia.employeehub.service.EmployeeHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class RetrieveAllEmployeeDetailsUseCaseImpl(
    private val service: EmployeeHubService
) : RetrieveAllEmployeeDetailsUseCase {

    override suspend fun execute(input: Unit): RetrieveAllEmployeeDetailsUseCase.Result =
        withContext(Dispatchers.IO) {
            when (val serviceResult = service.retrieveAllEmployeeDetails()) {
                is ApiResult.Success -> {
                    RetrieveAllEmployeeDetailsUseCase.Result.Success(serviceResult.data)
                }
                is ApiResult.FailureWithResponse,
                is ApiResult.FailureWithException -> {
                    RetrieveAllEmployeeDetailsUseCase.Result.Failure
                }
            }
        }
}
