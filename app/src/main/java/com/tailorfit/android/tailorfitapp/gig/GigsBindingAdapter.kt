package com.tailorfit.android.tailorfitapp.gig

import android.util.Log
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
fun TextView.bindTextViews(string: String?){
    string.let {
        text = it
    }
}