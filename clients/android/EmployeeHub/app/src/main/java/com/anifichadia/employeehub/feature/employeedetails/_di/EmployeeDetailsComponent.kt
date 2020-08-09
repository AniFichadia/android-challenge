package com.anifichadia.employeehub.feature.employeedetails._di

import com.anifichadia.employeehub.feature.employeedetails.EmployeeDetailsActivity
import com.anifichadia.employeehub.feature.employeedetails.EmployeeDetailsContract
import com.anifichadia.employeehub.framework.dependencyinjection.AppComponent
import com.anifichadia.employeehub.framework.dependencyinjection.PerActivity
import com.anifichadia.employeehub.framework.dependencyinjection.UseCaseModule
import dagger.Component

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-09
 */
@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [
        EmployeeDetailsModule::class,
        UseCaseModule::class
    ]
)
interface EmployeeDetailsComponent {
    fun createViewModel(): EmployeeDetailsContract.ViewModel

    fun inject(activity: EmployeeDetailsActivity)
}
