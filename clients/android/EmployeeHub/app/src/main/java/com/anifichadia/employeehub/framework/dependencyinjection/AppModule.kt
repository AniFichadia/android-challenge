package com.anifichadia.employeehub.framework.dependencyinjection

import android.content.Context
import com.anifichadia.employeehub.shared.AppConfiguration
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
@Module
class AppModule(
    private val appContext: Context
) {
    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplicationContext(): Context = appContext

    @Provides
    @Singleton
    fun provideConfiguration(): AppConfiguration = AppConfiguration(
        debug = true
    )
}
