package com.tailorfit.android.tailorfitapp.models.request
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class SignUpRequest(
    @SerializedName("business_name")
    val businessName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone_number")
    val phoneNumber: String
) : Parcelable