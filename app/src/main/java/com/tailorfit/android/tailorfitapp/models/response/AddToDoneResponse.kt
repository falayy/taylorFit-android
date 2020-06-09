package com.tailorfit.android.tailorfitapp.models.response


import com.google.gson.annotations.SerializedName

data class AddToDoneResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?
)