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
package com.tailorfit.android.tailorfitapp.gig

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tailorfit.android.databinding.IndividualStyleImagesImageViewBinding
import com.tailorfit.android.tailorfitapp.models.request.GigImageModel

class AddGigImageDetailsAdapter constructor(
    private val imageOnClickListener: OnclickListener
) :
    ListAdapter<GigImageModel,
            AddGigImageDetailsAdapter.GigImageViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GigImageViewHolder {
        return GigImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GigImageViewHolder, position: Int) {
        val items = getItem(position)
        holder.bind(items)
        holder.itemView.setOnClickListener {
            imageOnClickListener.onClickItem(holder, holder.adapterPosition)
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

    interface OnclickListener {
        fun onClickItem(gigImageViewHolder: GigImageViewHolder, itemPosition: Int)
    }
}
