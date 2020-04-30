package com.tailorfit.android.tailorfitapp.gig

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tailorfit.android.databinding.IndividualStyleImagesImageViewBinding
import com.tailorfit.android.tailorfitapp.models.request.GigImageModel

class AddGigImageDetailsAdapter(
    private val imageOnClickListener: OnclickListener,
    private var imageView: ImageView
) :
    ListAdapter<GigImageModel, AddGigImageDetailsAdapter.GigImageViewHolder>(ImageDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GigImageViewHolder {
        return GigImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GigImageViewHolder, position: Int) {
        val items = getItem(position)
        holder.bind(items)
        imageView = holder.binding.imageUploadStatusView
        holder.itemView.setOnClickListener {
            imageOnClickListener.onClickItem(holder.adapterPosition)
        }
    }

    class GigImageViewHolder private constructor(
        var binding:
        IndividualStyleImagesImageViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(items: GigImageModel) {
            binding.imageModel = items
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): GigImageViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                var binding = IndividualStyleImagesImageViewBinding.inflate(
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

    class OnclickListener(val clickListener: (itemPosition: Int) -> Unit) {
        fun onClickItem(itemPosition: Int) = clickListener(itemPosition)
    }
}