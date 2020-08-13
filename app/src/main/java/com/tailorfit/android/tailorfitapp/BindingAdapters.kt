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
package com.tailorfit.android.tailorfitapp

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.google.android.material.textfield.TextInputEditText
import com.tailorfit.android.Constants
import com.tailorfit.android.R
import com.tailorfit.android.tailorfitapp.models.response.CustomerInfoResponseModel
import com.tailorfit.android.tailorfitapp.userdashboard.DashBoardAdapter
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
fun bindGender(imgView: ImageView, string: String?) {
    string?.let {
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
fun bindStyle(textView: TextView, string: String?) {
    string?.let {
        textView.text = it
    }
}

@BindingAdapter("customerName")
fun bindCustomerName(textView: TextView, string: String?) {
    string?.let {
        textView.text = it
    }
}

@BindingAdapter("stylePrice")
fun bindPrice(textView: TextView, string: String?) {
    string?.let {
        textView.text = it
    }
}

@BindingAdapter("styleDate")
fun bindDate(textView: TextView, string: String?) {
    string?.let {
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
fun bindMeasurementValue(textInputEditText: TextInputEditText, string: String) {
    string.let {
        textInputEditText.setText(it)
    }
}

