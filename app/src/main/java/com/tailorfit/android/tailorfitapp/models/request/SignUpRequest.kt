package com.tailorfit.android.tailorfitapp.models.request
import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @SerializedName("business_name")
    val businessName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: Long
)