package com.anifichadia.employeehub.feature.employeelist._di

import com.anifichadia.employeehub.feature.employeelist.EmployeeListContract
import com.anifichadia.employeehub.feature.employeelist.EmployeeListViewModel
import com.anifichadia.employeehub.shared.usecase.RetrieveAllEmployeeDetailsUseCase
import dagger.Module
import dagger.Provides

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
@Module
class EmployeeListModule {

    @Provides
    fun provideViewModel(
        retrieveAllEmployeeDetailsUseCase: RetrieveAllEmployeeDetailsUseCase
    ): EmployeeListContract.ViewModel = EmployeeListViewModel(
        retrieveAllEmployeeDetailsUseCase
    )
}
