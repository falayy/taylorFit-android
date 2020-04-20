package com.tailorfit.android.tailorfitapp.models.request


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreateGigRequest(
    @SerializedName("customer_id")
    var customerId: String? = null,
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("notes")
    var notes: String? = null,
    @SerializedName("price")
    var price: String? = null,
    @SerializedName("style")
    var style: List<String>?     = null,
    @SerializedName("style_name")
    var styleName: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("user_id")
    var userId: String?  = null
) : Parcelable