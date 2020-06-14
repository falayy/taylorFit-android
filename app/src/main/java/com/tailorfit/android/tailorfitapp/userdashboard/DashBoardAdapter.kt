/**
 * Copyright (c) 2020 Falaye Iyanuoluwa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tailorfit.android.tailorfitapp.userdashboard

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

        val items: CustomerInfoResponseModel? = getItem(position)
        holder.itemView.setOnClickListener {
            customerJobDetailsOnClickListener.onClick(items)
        }

        holder.bind(items)
    }

    class DashBoardViewHolder private constructor(val binding: IndividualCustomerDashboardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            customerInfoResponseModel: CustomerInfoResponseModel?
        ) {
            binding.customerInfo = customerInfoResponseModel
            binding.executePendingBindings()
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
        val clickListener: (
            customerInfoResponseModel: CustomerInfoResponseModel?
        ) -> Unit
    ) {
        fun onClick(customerInfoResponseModel: CustomerInfoResponseModel?) =
            clickListener(customerInfoResponseModel)
    }
}
