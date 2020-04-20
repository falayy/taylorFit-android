package com.tailorfit.android.tailorfitapp.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tailorfit.android.databinding.IndividualCustomerDashboardBinding
import com.tailorfit.android.tailorfitapp.models.response.CustomerInfoResponseModel

class DashBoardAdapter(

    private val customerJobDetailsOnClickListener: OnclickListener

) : ListAdapter<CustomerInfoResponseModel, DashBoardAdapter.DashBoardViewHolder>(

    DashBoardDiffUtils()

) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashBoardViewHolder {

        return DashBoardViewHolder.from(parent)

    }

    override fun onBindViewHolder(holder: DashBoardViewHolder, position: Int) {

        val items = getItem(position)
        holder.itemView.setOnClickListener {
            customerJobDetailsOnClickListener.onClick(items)
        }
        holder.bind(items)
    }


    class DashBoardViewHolder private constructor(val binding: IndividualCustomerDashboardBinding) :

        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            customerInfoResponseModel: CustomerInfoResponseModel
        ) {

        }

        companion object {
            fun from(parent: ViewGroup): DashBoardViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    IndividualCustomerDashboardBinding.inflate(layoutInflater, parent, false)
                return DashBoardViewHolder(binding)
            }
        }
    }


    class DashBoardDiffUtils : DiffUtil.ItemCallback<CustomerInfoResponseModel>() {

        override fun areItemsTheSame(
            oldItem: CustomerInfoResponseModel,
            newItem: CustomerInfoResponseModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: CustomerInfoResponseModel,
            newItem: CustomerInfoResponseModel
        ): Boolean {
            return oldItem == newItem
        }

    }

    class OnclickListener(
        val clickListener:
            (
            customerInfoResponseModel: CustomerInfoResponseModel
        ) -> Unit
    ) {
        fun onClick(customerInfoResponseModel: CustomerInfoResponseModel) =
            clickListener(customerInfoResponseModel)
    }
}