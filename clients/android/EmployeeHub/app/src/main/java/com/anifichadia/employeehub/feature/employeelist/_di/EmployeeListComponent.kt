package com.anifichadia.employeehub.feature.employeelist._di

import com.anifichadia.employeehub.feature.employeelist.EmployeeListActivity
import com.anifichadia.employeehub.feature.employeelist.EmployeeListContract
import com.anifichadia.employeehub.framework.dependencyinjection.AppComponent
import com.anifichadia.employeehub.framework.dependencyinjection.PerActivity
import com.anifichadia.employeehub.framework.dependencyinjection.UseCaseModule
import dagger.Component

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
@PerActivity
@Component(
    dependencies = [AppComponent::class],
    modules = [
        EmployeeListModule::class,
        UseCaseModule::class
    ]
)
interface EmployeeListComponent {
    fun createViewModel(): EmployeeListContract.ViewModel

    fun inject(activity: EmployeeListActivity)
}
