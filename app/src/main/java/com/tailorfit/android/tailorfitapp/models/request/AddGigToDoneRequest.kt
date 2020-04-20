package com.tailorfit.android.tailorfitapp.models.request


import com.google.gson.annotations.SerializedName

data class AddGigToDoneRequest(
    @SerializedName("customer_id")
    val customerId: String?,
    @SerializedName("gig_id")
    val gigId: String?
)