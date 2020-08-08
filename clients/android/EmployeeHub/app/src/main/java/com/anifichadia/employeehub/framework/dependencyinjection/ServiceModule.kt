package com.anifichadia.employeehub.framework.dependencyinjection

import android.content.Context
import com.anifichadia.employeehub.service.EmployeeHubService
import com.anifichadia.employeehub.service.EmployeeHubServiceImpl
import com.anifichadia.employeehub.service.retrofit.DefaultOkHttpClientFactory
import com.anifichadia.employeehub.service.retrofit.DefaultRetrofitFactory
import com.anifichadia.employeehub.service.retrofit.OkHttpClientFactory
import com.anifichadia.employeehub.service.retrofit.RetrofitFactory
import com.anifichadia.employeehub.shared.AppConfiguration
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideOkHttpClientFactory(
        @ApplicationContext context: Context,
        appConfiguration: AppConfiguration
    ): OkHttpClientFactory = DefaultOkHttpClientFactory(
        context,
        appConfiguration.serviceConfiguration
    )

    @Provides
    @Singleton
    fun provideRetrofitFactory(
        appConfiguration: AppConfiguration,
        okHttpClientFactory: OkHttpClientFactory
    ): RetrofitFactory = DefaultRetrofitFactory(
        appConfiguration.serviceConfiguration,
        okHttpClientFactory
    )

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitFactory: RetrofitFactory
    ): Retrofit = retrofitFactory.createRetrofit()


    @Provides
    @Singleton
    fun provideEmployeeHubService(
        retrofit: Retrofit
    ): EmployeeHubService = EmployeeHubServiceImpl(retrofit)
}
