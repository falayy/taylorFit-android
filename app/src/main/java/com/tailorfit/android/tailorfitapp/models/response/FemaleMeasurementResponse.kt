package com.tailorfit.android.tailorfitapp.models.response


import com.google.gson.annotations.SerializedName

data class FemaleMeasurementResponse(
    @SerializedName("_id")
    val _id: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null,
    @SerializedName("customer_id")
    val customerId: Int? = null,
    @SerializedName("gig_id")
    val gigId: Int? = null,
    @SerializedName("arm_hole")
    val armHole: Int? = null,
    @SerializedName("arm_round")
    val armRound: Int? = null,
    @SerializedName("bust_line")
    val bustLine: Int? = null,
    @SerializedName("full_length")
    val fullLength: Int? = null,
    @SerializedName("half_sleeve")
    val halfSleeve: Int? = null,
    @SerializedName("hip_line")
    val hipLine: Int? = null,
    @SerializedName("hip_round")
    val hipRound: Int? = null,
    @SerializedName("natural_waist_line")
    val naturalWaistLine: Int? = null,
    @SerializedName("natural_waist_round")
    val naturalWaistRound: Int? = null,
    @SerializedName("shoulder_shoulder")
    val shoulderShoulder: Int? = null,
    @SerializedName("sleeve_length")
    val sleeveLength: Int? = null,
    @SerializedName("under_bust")
    val underBust: Int? = null
)