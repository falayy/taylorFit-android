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

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tailorfit.android.tailorfitapp.models.request.GigImageModel

@BindingAdapter("listImagePlaceHolder")
fun bindRecyclerViews(recyclerView: RecyclerView, imagesPlaceHolder: List<GigImageModel>?) {
    imagesPlaceHolder?.let {
        val adapter = recyclerView.adapter as AddGigImageDetailsAdapter
        adapter.submitList(imagesPlaceHolder)
    }
}

@BindingAdapter("imagePlaceholderLabels")
fun TextView.bindTextViews(string: String?) {
    string.let {
        text = it
    }
}
