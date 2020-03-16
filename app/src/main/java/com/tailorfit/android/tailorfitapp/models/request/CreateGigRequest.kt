package com.tailorfit.android.tailorfitapp.models.request


import com.google.gson.annotations.SerializedName

data class CreateGigRequest(
    @SerializedName("customer_id")
    var customerId: String,
    @SerializedName("date")
    var date: String?,
    @SerializedName("notes")
    var notes: String?,
    @SerializedName("price")
    var price: String?,
    @SerializedName("style")
    var style: List<String>?,
    @SerializedName("style_name")
    var styleName: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("user_id")
    var userId: String?
)