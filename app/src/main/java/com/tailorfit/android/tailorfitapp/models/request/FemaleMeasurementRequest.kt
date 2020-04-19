package com.tailorfit.android.tailorfitapp.models.request


import com.google.gson.annotations.SerializedName

data class FemaleMeasurementRequest(
    @SerializedName("arm_hole")
    val armHole: Int? = null,
    @SerializedName("arm_round")
    val armRound: Int? = null,
    @SerializedName("bust_line")
    val bustLine: Int? = null,
    @SerializedName("bust_round")
    val bustRound: Int? = null,
    @SerializedName("customer_id")
    val customerId: String? = null,
    @SerializedName("full_length")
    val fullLength: Int? = null,
    @SerializedName("gig_id")
    val gigId: String? = null,
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
    val underBust: Int? = null,
    @SerializedName("user_id")
    val userId: String? = null
)