package com.anifichadia.employeehub.feature.employeelist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anifichadia.employeehub.R
import com.anifichadia.employeehub.databinding.ListItemEmployeeBinding
import com.anifichadia.employeehub.service.domain.Employee
import com.anifichadia.employeehub.shared.toBitmap
import kotlin.properties.Delegates

typealias ListItemClickListener = (Employee, Int) -> Unit

/**
 * @author Aniruddh Fichadia
 * @date 2020-08-08
 */
class EmployeeListAdapter(
    context: Context
) : RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    var employeeList: List<Employee> by Delegates.observable(emptyList()) { _, _, _ -> notifyDataSetChanged() }

    var listItemClickListener: ListItemClickListener? = null


    override fun getItemCount(): Int = employeeList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        val binding = ListItemEmployeeBinding.inflate(layoutInflater, parent, false)

        return EmployeeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(employeeList[position])
    }


    inner class EmployeeViewHolder(
        private val binding: ListItemEmployeeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(employee: Employee) {
            itemView.setOnClickListener {
                listItemClickListener?.invoke(employee, adapterPosition)
            }

            // This could potentially be cached using an LRU cache
            val thumbnail = employee.imageThumbnail
            if (thumbnail != null) {
                binding.listItemEmployeeImgThumbnail.setImageBitmap(thumbnail.toBitmap())
            } else {
                binding.listItemEmployeeImgThumbnail.setImageResource(R.drawable.ic_image_unavailable)
            }
            binding.listItemEmployeeTextName.text = itemView.context.getString(
                R.string.format_employee_full_name,
                employee.firstName,
                employee.lastName
            )
            binding.listItemEmployeeTextGender.text = employee.gender
            binding.listItemEmployeeTextGender.contentDescription = itemView.context.getString(
                R.string.employee_list_format_content_description_gender,
                employee.gender
            )
            binding.listItemEmployeeBirthDate.text = employee.birthDate.toString()
            binding.listItemEmployeeBirthDate.contentDescription = itemView.context.getString(
                R.string.employee_list_format_content_description_birth_date,
                employee.birthDate.toString()
            )
        }
    }
}
