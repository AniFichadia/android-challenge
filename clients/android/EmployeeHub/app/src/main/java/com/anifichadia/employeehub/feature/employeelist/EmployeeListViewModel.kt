package com.anifichadia.employeehub.feature.employeelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anifichadia.employeehub.service.domain.Employee
import com.anifichadia.employeehub.shared.Event
import com.anifichadia.employeehub.shared.usecase.RetrieveAllEmployeeDetailsUseCase
import kotlinx.coroutines.launch

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class EmployeeListViewModel(
    private val retrieveAllEmployeeDetailsUseCase: RetrieveAllEmployeeDetailsUseCase
) : EmployeeListContract.ViewModel() {

    internal val _loadingState = MutableLiveData<EmployeeListContract.LoadingState>()
    override val loadingState: LiveData<EmployeeListContract.LoadingState> = _loadingState

    internal val _employeeList = MutableLiveData<List<Employee>>()
    override val employeeList: LiveData<List<Employee>> = _employeeList

    internal val _viewEmployeeDetails = MutableLiveData<Event<Employee>>()
    override val viewEmployeeDetails: MutableLiveData<Event<Employee>> = _viewEmployeeDetails


    override fun loadEmployeeListIfRequired() {
        // If already loading or loaded successfully, then prevent invocation as it's already in progress or not required
        if (_loadingState.value == EmployeeListContract.LoadingState.LOADING || _loadingState.value == EmployeeListContract.LoadingState.SUCCESS) return

        _loadingState.postValue(EmployeeListContract.LoadingState.LOADING)
        launch {
            when (val allEmployeeDetailsResult = retrieveAllEmployeeDetailsUseCase.execute(Unit)) {
                is RetrieveAllEmployeeDetailsUseCase.Result.Success -> {
                    val employees = allEmployeeDetailsResult.employees

                    _loadingState.postValue(EmployeeListContract.LoadingState.SUCCESS)
                    _employeeList.postValue(employees)
                }
                is RetrieveAllEmployeeDetailsUseCase.Result.Failure -> {
                    _loadingState.postValue(EmployeeListContract.LoadingState.ERROR)
                }
            }
        }
    }

    override fun onUserSelectedEmployee(employee: Employee) {
        _viewEmployeeDetails.postValue(Event(employee))
    }
}
