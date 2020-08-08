package com.anifichadia.employeehub.framework.dependencyinjection

import android.content.Context
import com.anifichadia.employeehub.EmployeeHubApplication
import com.anifichadia.employeehub.service.EmployeeHubService
import dagger.Component
import javax.inject.Singleton

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        ServiceModule::class
    ]
)
interface AppComponent {
    fun inject(app: EmployeeHubApplication)


    @ApplicationContext
    fun getAppContext(): Context

    fun getEmployeeHubService(): EmployeeHubService
}
