package com.tailorfit.android.tailorfitapp.models.response

import com.google.gson.annotations.SerializedName

data class MeasurementResponse(
    @SerializedName("_id")
    val _id: String? = null,
    @SerializedName("user_id")
    val userId: String? = null,
    @SerializedName("customer_id")
    val customerId: String? = null,
    @SerializedName("gig_id")
    val gigId: String? = null,
    @SerializedName("measurement")
    val measurement : Map<String, String>
)