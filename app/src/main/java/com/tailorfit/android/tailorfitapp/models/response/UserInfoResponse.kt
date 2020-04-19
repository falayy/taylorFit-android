package com.tailorfit.android.tailorfitapp.models.response


import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("business_name")
    val businessName: String? = null,
    @SerializedName("phone_number")
    val phoneNumber: String? = null,
    @SerializedName("username")
    val username: String? = null
)