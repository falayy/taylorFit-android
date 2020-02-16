package com.tailorfit.android.tailorfitapp.models.request
import com.google.gson.annotations.SerializedName

data class SignUpRespone(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("token")
    val token: String
)