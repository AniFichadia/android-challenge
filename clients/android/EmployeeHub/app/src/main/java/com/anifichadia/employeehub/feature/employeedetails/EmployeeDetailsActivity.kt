package com.anifichadia.employeehub.feature.employeedetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.anifichadia.employeehub.EmployeeHubApplication
import com.anifichadia.employeehub.R
import com.anifichadia.employeehub.databinding.ActivityEmployeeDetailsBinding
import com.anifichadia.employeehub.feature.employeedetails._di.DaggerEmployeeDetailsComponent
import com.anifichadia.employeehub.feature.employeedetails._di.EmployeeDetailsComponent
import com.anifichadia.employeehub.feature.employeedetails._di.EmployeeDetailsModule
import com.anifichadia.employeehub.framework.ViewModelInitialiser
import com.anifichadia.employeehub.framework.dependencyinjection.UseCaseModule
import com.anifichadia.employeehub.shared.toBitmap

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-09
 */
class EmployeeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEmployeeDetailsBinding
    private lateinit var component: EmployeeDetailsComponent
    private lateinit var viewModel: EmployeeDetailsContract.ViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        component = DaggerEmployeeDetailsComponent.builder()
            .appComponent((application as EmployeeHubApplication).appComponent)
            .employeeDetailsModule(EmployeeDetailsModule())
            .useCaseModule(UseCaseModule())
            .build()

        viewModel = ViewModelInitialiser.forActivity(this, component::createViewModel)
        viewModel.employeeId = intent.getIntExtra(KEY_EMPLOYEE_ID, 0)


        //region View binding
        binding.employeeDetailsBtnReload.setOnClickListener {
            viewModel.loadEmployeeDetailsIfRequired()
        }
        //endregion

        //region View model observer bindings
        viewModel.loadingState.observe(this) { loadingState ->
            binding.employeeDetailsContainerLoading.visibility =
                if (loadingState == EmployeeDetailsContract.LoadingState.LOADING) {
                    VISIBLE
                } else {
                    GONE
                }
            binding.employeeDetailsContainerError.visibility =
                if (loadingState == EmployeeDetailsContract.LoadingState.ERROR) {
                    VISIBLE
                } else {
                    GONE
                }
            binding.employeeDetailsContainerSuccess.visibility =
                if (loadingState == EmployeeDetailsContract.LoadingState.SUCCESS) {
                    VISIBLE
                } else {
                    GONE
                }
        }

        viewModel.employee.observe(this) { employee ->
            binding.employeeDetailsTextName.text =
                getString(R.string.format_employee_full_name, employee.firstName, employee.lastName)
            val image = employee.image
            if (image != null) {
                binding.employeeDetailsImageEmployee.setImageBitmap(image.toBitmap())
            } else {
                binding.employeeDetailsImageEmployee.setImageResource(R.drawable.ic_image_unavailable)
            }
        }
        //endregion


        viewModel.loadEmployeeDetailsIfRequired()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    companion object {
        private const val KEY_EMPLOYEE_ID = "KEY_EMPLOYEE_ID"

        fun newInstance(context: Context, employeeId: Int): Intent {
            return Intent(context, EmployeeDetailsActivity::class.java).apply {
                putExtra(KEY_EMPLOYEE_ID, employeeId)
            }
        }
    }
}
