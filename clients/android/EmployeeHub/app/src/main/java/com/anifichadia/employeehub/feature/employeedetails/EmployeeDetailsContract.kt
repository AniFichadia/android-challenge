package com.anifichadia.employeehub.feature.employeedetails

import androidx.lifecycle.LiveData
import com.anifichadia.employeehub.framework.MvvmContract
import com.anifichadia.employeehub.service.domain.Employee

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-09
 */
interface EmployeeDetailsContract : MvvmContract {

    abstract class ViewModel : MvvmContract.ViewModel() {
        abstract var employeeId: Int

        abstract val loadingState: LiveData<LoadingState>
        abstract val employee: LiveData<Employee>

        abstract fun loadEmployeeDetailsIfRequired()
    }

    enum class LoadingState {
        LOADING, SUCCESS, ERROR
    }
}
