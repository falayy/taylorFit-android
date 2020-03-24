package com.tailorfit.android.tailorfitapp.gig

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tailorfit.android.databinding.IndividualStyleImagesImageViewBinding
import com.tailorfit.android.tailorfitapp.models.request.GigImageModel

class AddGigImageDetailsAdapter(private val imageOnClickListener: OnclickListener) :
    ListAdapter<GigImageModel, AddGigImageDetailsAdapter.GigImageViewHolder>(ImageDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GigImageViewHolder {
        return GigImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GigImageViewHolder, position: Int) {
        val items = getItem(position)
        holder.bind(items)
        holder.itemView.setOnClickListener {
            imageOnClickListener.onClick(items)
        }
    }

    class GigImageViewHolder private constructor(
        val binding:
        IndividualStyleImagesImageViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: GigImageModel) {
            binding.imageModel = items
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GigImageViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = IndividualStyleImagesImageViewBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
                return GigImageViewHolder(binding)
            }
        }

    }

    class ImageDiffCallback : DiffUtil.ItemCallback<GigImageModel>() {
        override fun areItemsTheSame(oldItem: GigImageModel, newItem: GigImageModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GigImageModel, newItem: GigImageModel): Boolean {
            return oldItem == newItem
        }

    }

    class OnclickListener(val clickListener: (imageModel: GigImageModel) -> Unit) {
        fun onClick(imageModel: GigImageModel) = clickListener(imageModel)
    }
}