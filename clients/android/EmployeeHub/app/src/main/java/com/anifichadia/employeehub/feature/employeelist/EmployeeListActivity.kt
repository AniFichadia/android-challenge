package com.anifichadia.employeehub.feature.employeelist

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.anifichadia.employeehub.EmployeeHubApplication
import com.anifichadia.employeehub.databinding.ActivityEmployeeListBinding
import com.anifichadia.employeehub.feature.employeelist._di.DaggerEmployeeListComponent
import com.anifichadia.employeehub.feature.employeelist._di.EmployeeListComponent
import com.anifichadia.employeehub.feature.employeelist._di.EmployeeListModule
import com.anifichadia.employeehub.framework.ViewModelInitialiser
import com.anifichadia.employeehub.framework.dependencyinjection.UseCaseModule

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class EmployeeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeListBinding
    private lateinit var component: EmployeeListComponent
    private lateinit var viewModel: EmployeeListContract.ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmployeeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        component = DaggerEmployeeListComponent.builder()
            .appComponent((application as EmployeeHubApplication).appComponent)
            .employeeListModule(EmployeeListModule())
            .useCaseModule(UseCaseModule())
            .build()

        viewModel = ViewModelInitialiser.forActivity(this, component::createViewModel)

        //region View configuration
        binding.employeeListBtnReload.setOnClickListener {
            viewModel.loadEmployeeListIfRequired()
        }

        val adapter = EmployeeListAdapter(this)
        binding.employeeListRecyclerviewEmployees.layoutManager = LinearLayoutManager(this)
        binding.employeeListRecyclerviewEmployees.adapter = adapter
        //endregion

        //region View model observer bindings
        viewModel.loadingState.observe(this) { loadingState ->
            binding.employeeListContainerLoading.visibility =
                if (loadingState == EmployeeListContract.LoadingState.LOADING) {
                    VISIBLE
                } else {
                    GONE
                }
            binding.employeeListContainerError.visibility =
                if (loadingState == EmployeeListContract.LoadingState.ERROR) {
                    VISIBLE
                } else {
                    GONE
                }
            binding.employeeListContainerSuccess.visibility =
                if (loadingState == EmployeeListContract.LoadingState.SUCCESS) {
                    VISIBLE
                } else {
                    GONE
                }
        }

        viewModel.employeeList.observe(this) { employeeList ->
            adapter.employeeList = employeeList
        }
        //endregion


        viewModel.loadEmployeeListIfRequired()
    }
}
