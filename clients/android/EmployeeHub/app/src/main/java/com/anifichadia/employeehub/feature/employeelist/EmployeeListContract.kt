package com.anifichadia.employeehub.feature.employeelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anifichadia.employeehub.framework.MvvmContract
import com.anifichadia.employeehub.service.domain.Employee
import com.anifichadia.employeehub.shared.Event

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
interface EmployeeListContract : MvvmContract {

    abstract class ViewModel : MvvmContract.ViewModel() {
        abstract val loadingState: LiveData<LoadingState>
        abstract val employeeList: LiveData<List<Employee>>

        abstract val viewEmployeeDetails: MutableLiveData<Event<Employee>>

        abstract fun loadEmployeeListIfRequired()

        abstract fun onUserSelectedEmployee(employee: Employee)
    }

    enum class LoadingState {
        LOADING, SUCCESS, ERROR
    }
}
