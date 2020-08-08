package com.anifichadia.employeehub.feature.employeelist

import androidx.lifecycle.LiveData
import com.anifichadia.employeehub.framework.MvvmContract
import com.anifichadia.employeehub.service.domain.Employee

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
interface EmployeeListContract : MvvmContract {

    abstract class ViewModel : MvvmContract.ViewModel() {
        abstract val loadingState: LiveData<LoadingState>
        abstract val employeeList: LiveData<List<Employee>>

        abstract fun loadEmployeeListIfRequired()
    }

    enum class LoadingState {
        LOADING, SUCCESS, ERROR
    }
}
