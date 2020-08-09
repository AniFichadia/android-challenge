package com.anifichadia.employeehub.feature.employeedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anifichadia.employeehub.service.domain.Employee
import com.anifichadia.employeehub.shared.usecase.RetrieveEmployeeDetailsUseCase
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-09
 */
class EmployeeDetailsViewModel(
    private val retrieveEmployeeDetailsUseCase: RetrieveEmployeeDetailsUseCase
) : EmployeeDetailsContract.ViewModel() {

    override var employeeId by Delegates.notNull<Int>()


    internal val _loadingState = MutableLiveData<EmployeeDetailsContract.LoadingState>()
    override val loadingState: LiveData<EmployeeDetailsContract.LoadingState> = _loadingState

    internal val _employee = MutableLiveData<Employee>()
    override val employee: LiveData<Employee> = _employee


    override fun loadEmployeeDetailsIfRequired() {
        // If already loading or loaded successfully, then prevent invocation as it's already in progress or not required
        if (_loadingState.value == EmployeeDetailsContract.LoadingState.SUCCESS || _loadingState.value == EmployeeDetailsContract.LoadingState.LOADING) return

        _loadingState.postValue(EmployeeDetailsContract.LoadingState.LOADING)
        launch {
            when (val employeeDetailsResult = retrieveEmployeeDetailsUseCase.execute(employeeId)) {
                is RetrieveEmployeeDetailsUseCase.Result.Success -> {
                    val employee = employeeDetailsResult.employee

                    _loadingState.postValue(EmployeeDetailsContract.LoadingState.SUCCESS)
                    _employee.postValue(employee)
                }
                is RetrieveEmployeeDetailsUseCase.Result.Failure -> {
                    _loadingState.postValue(EmployeeDetailsContract.LoadingState.ERROR)
                }
            }
        }
    }
}
