package com.tailorfit.android.tailorfitapp.models.response
import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("token")
    val token: String
)