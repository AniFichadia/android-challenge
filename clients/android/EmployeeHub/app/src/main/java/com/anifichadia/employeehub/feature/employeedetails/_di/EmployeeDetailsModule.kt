package com.anifichadia.employeehub.feature.employeedetails._di

import com.anifichadia.employeehub.feature.employeedetails.EmployeeDetailsContract
import com.anifichadia.employeehub.feature.employeedetails.EmployeeDetailsViewModel
import com.anifichadia.employeehub.shared.usecase.RetrieveEmployeeDetailsUseCase
import dagger.Module
import dagger.Provides

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-09
 */
@Module
class EmployeeDetailsModule {

    @Provides
    fun provideViewModel(
        retrieveEmployeeDetailsUseCase: RetrieveEmployeeDetailsUseCase
    ): EmployeeDetailsContract.ViewModel = EmployeeDetailsViewModel(
        retrieveEmployeeDetailsUseCase
    )
}
