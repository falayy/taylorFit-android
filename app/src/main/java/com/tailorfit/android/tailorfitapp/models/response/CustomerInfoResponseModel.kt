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
package com.tailorfit.android.tailorfitapp.models.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CustomerInfoResponseModel(
    @SerializedName("customer_gender")
    var customerGender: String? = null,
    @SerializedName("customer_id")
    var customerId: String? = null,
    @SerializedName("customer_name")
    var customerName: String? = null,
    @SerializedName("customer_number")
    var customerNumber: String? = null,
    @SerializedName("delivery_date")
    var deliveryDate: String? = null,
    @SerializedName("gig_id")
    var gigId: String? = null,
    @SerializedName("gig_title")
    var gigTitle: String? = null,
    @SerializedName("is_done")
    var isDone: Boolean? = null,
    @SerializedName("notes")
    var notes: String? = null,
    @SerializedName("price")
    var price: Int? = null,
    @SerializedName("style")
    var style: List<String?>? = null,
    @SerializedName("style_name")
    var styleName: String? = null
) : Parcelable
