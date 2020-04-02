package com.tailorfit.android.tailorfitapp.models.request


import com.google.gson.annotations.SerializedName

data class SignInRequest(
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)