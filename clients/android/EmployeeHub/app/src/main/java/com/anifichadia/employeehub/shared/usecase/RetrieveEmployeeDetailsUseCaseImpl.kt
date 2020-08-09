package com.anifichadia.employeehub.shared.usecase

import com.anifichadia.employeehub.service.ApiResult
import com.anifichadia.employeehub.service.EmployeeHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrieveEmployeeDetailsUseCaseImpl(
    private val service: EmployeeHubService
) : RetrieveEmployeeDetailsUseCase {

    override suspend fun execute(employeeId: Int): RetrieveEmployeeDetailsUseCase.Result =
        withContext(Dispatchers.IO) {
            when (val serviceResult = service.retrieveEmployeeDetails(employeeId)) {
                is ApiResult.Success -> {
                    RetrieveEmployeeDetailsUseCase.Result.Success(serviceResult.data)
                }
                is ApiResult.FailureWithResponse,
                is ApiResult.FailureWithException -> {
                    RetrieveEmployeeDetailsUseCase.Result.Failure
                }
            }
        }
}
