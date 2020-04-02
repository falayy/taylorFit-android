package com.tailorfit.android.tailorfitapp.models.response


import com.google.gson.annotations.SerializedName

data class CreateCustomerResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: Long
)