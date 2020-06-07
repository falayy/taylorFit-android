package com.tailorfit.android.tailorfitapp

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.tailorfit.android.Constants
import com.tailorfit.android.R
import com.tailorfit.android.tailorfitapp.models.response.CustomerInfoResponseModel
import com.tailorfit.android.tailorfitapp.userdashboard.DashBoardAdapter
import com.tailorfit.android.utils.DateUtils
import timber.log.Timber

@BindingAdapter("listCustomerInfo")
fun bindRecyclerView(recyclerView: RecyclerView, customerInfo: List<CustomerInfoResponseModel>?) {
    customerInfo?.let {
        Timber.d("Update recycler view")
        val adapter = recyclerView.adapter as DashBoardAdapter
        adapter.submitList(customerInfo)
    }
}


/***
 * For Get Customer Info Adapter
 */

@BindingAdapter("gender")
fun bindGender(imgView: ImageView, string: String) {
    string.let {
        when (it) {
            Constants.Gender.FEMALE -> {
                imgView.load(R.drawable.ic_female)
            }

            Constants.Gender.MALE -> {
                imgView.load(R.drawable.ic_male)
            }

            else -> {
                // do nothing.
            }
        }
    }
}

@BindingAdapter("styleName")
fun bindStyle(textView: TextView, string: String) {
    string.let {
        textView.text = it
    }
}

@BindingAdapter("customerName")
fun bindCustomerName(textView: TextView, string: String) {
    string.let {
        textView.text = it
    }
}

@BindingAdapter("stylePrice")
fun bindPrice(textView: TextView, int: Int) {
    int.let {
        textView.text = it.toString()
    }
}

@BindingAdapter("styleDate")
fun bindDate(textView: TextView, string: String) {
    string.let {
        textView.text = it
    }
}


/***
 * For Measurement Adapter
 */

@BindingAdapter("measurementKey")
fun bindMeasurementKey(textView: TextView, string: String) {
    string.let {
        textView.text = it
    }
}

@BindingAdapter("measurementValue")
fun bindMeasurementValue(textView: TextView, string: String) {
    string.let {
        textView.text = it
    }
}


