package com.tailorfit.android.tailorfitapp.models.request


import com.google.gson.annotations.SerializedName

data class FemaleMeasurementRequest(
    @SerializedName("arm_hole")
    val armHole: Double = 0.0,
    @SerializedName("arm_round")
    val armRound: Double = 0.0,
    @SerializedName("bust_line")
    val bustLine: Double = 0.0,
    @SerializedName("bust_round")
    val bustRound: Double = 0.0,
    @SerializedName("customer_id")
    val customerId: String? = null,
    @SerializedName("full_length")
    val fullLength: Double = 0.0,
    @SerializedName("gig_id")
    val gigId: String? = null,
    @SerializedName("half_sleeve")
    val halfSleeve: Double = 0.0,
    @SerializedName("hip_line")
    val hipLine: Double = 0.0,
    @SerializedName("hip_round")
    val hipRound: Double = 0.0,
    @SerializedName("natural_waist_line")
    val naturalWaistLine: Double = 0.0,
    @SerializedName("natural_waist_round")
    val naturalWaistRound: Double = 0.0,
    @SerializedName("shoulder_shoulder")
    val shoulderShoulder: Double = 0.0,
    @SerializedName("sleeve_length")
    val sleeveLength: Double = 0.0,
    @SerializedName("under_bust")
    val underBust: Double = 0.0,
    @SerializedName("user_id")
    val userId: String? = null
)