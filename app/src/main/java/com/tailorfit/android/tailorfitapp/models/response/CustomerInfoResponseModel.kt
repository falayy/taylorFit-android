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