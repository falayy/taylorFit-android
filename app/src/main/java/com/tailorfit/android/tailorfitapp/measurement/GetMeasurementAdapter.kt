package com.tailorfit.android.tailorfitapp.measurement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tailorfit.android.databinding.IndividualMeasurementLayoutBinding
import com.tailorfit.android.tailorfitapp.measurement.GetMeasurementAdapter.*
import com.tailorfit.android.tailorfitapp.models.response.MeasurementMapperModel

class GetMeasurementAdapter :
    ListAdapter<MeasurementMapperModel,
            GetMeasurementViewHolder>
        (GetMeasurementDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GetMeasurementViewHolder {
        return GetMeasurementViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GetMeasurementViewHolder, position: Int) {
        val items = getItem(position)
        holder.bind(items)

    }


    class GetMeasurementViewHolder private constructor(
        val binding: IndividualMeasurementLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: MeasurementMapperModel) {
            binding.measurementModel = items
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GetMeasurementViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = IndividualMeasurementLayoutBinding.inflate(
                    inflater,
                    parent,
                    false
                )
                return GetMeasurementViewHolder(binding)
            }
        }
    }


    class GetMeasurementDiffUtil : DiffUtil.ItemCallback<MeasurementMapperModel>() {
        override fun areItemsTheSame(
            oldItem: MeasurementMapperModel,
            newItem: MeasurementMapperModel
        ): Boolean {
            return oldItem.measurementName == newItem.measurementName
        }

        override fun areContentsTheSame(
            oldItem: MeasurementMapperModel,
            newItem: MeasurementMapperModel
        ): Boolean {
            return oldItem.measurementValue == newItem.measurementValue
        }


    }


}