package com.anifichadia.employeehub

import android.app.Application
import com.anifichadia.employeehub.framework.dependencyinjection.AppComponent
import com.anifichadia.employeehub.framework.dependencyinjection.AppModule
import com.anifichadia.employeehub.framework.dependencyinjection.DaggerAppComponent
import com.anifichadia.employeehub.framework.dependencyinjection.ServiceModule
import com.anifichadia.employeehub.shared.AppConfiguration
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class EmployeeHubApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(appModule)
            .serviceModule(serviceModule)
            .build()
    }
    private val appModule: AppModule by lazy { AppModule(this) }
    private val serviceModule: ServiceModule by lazy { ServiceModule() }

    @Inject
    lateinit var configuration: AppConfiguration


    override fun onCreate() {
        super.onCreate()

        setup()
    }

    private fun setup() {
        inject()

        applyConfiguration()
    }

    private fun inject() {
        appComponent.inject(this)
    }

    private fun applyConfiguration() {
        Timber.uprootAll()

        if (configuration.debug) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
