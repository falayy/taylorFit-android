package com.tailorfit.android.tailorfitapp.models.request


import com.google.gson.annotations.SerializedName

data class CreateCustomerRequest(
    @SerializedName("gender")
    var gender: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("phone_number")
    var phoneNumber: String? = null,
    @SerializedName("user_id")
    var userId: String? = null
)