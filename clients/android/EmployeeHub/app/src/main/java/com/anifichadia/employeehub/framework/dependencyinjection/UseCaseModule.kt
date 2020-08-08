package com.anifichadia.employeehub.framework.dependencyinjection

import com.anifichadia.employeehub.service.EmployeeHubService
import com.anifichadia.employeehub.shared.usecase.RetrieveAllEmployeeDetailsUseCase
import com.anifichadia.employeehub.shared.usecase.RetrieveAllEmployeeDetailsUseCaseImpl
import dagger.Module
import dagger.Provides

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
@Module
class UseCaseModule {
    @Provides
    fun provideRetrieveAllEmployeeDetailsUseCase(
        employeeHubService: EmployeeHubService
    ): RetrieveAllEmployeeDetailsUseCase = RetrieveAllEmployeeDetailsUseCaseImpl(employeeHubService)
}
