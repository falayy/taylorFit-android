package com.tailorfit.android.tailorfitapp.models.response

import com.google.gson.annotations.SerializedName

data class MaleMeasurementResponse(
    @SerializedName("_id")
    val _id: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null,
    @SerializedName("customer_id")
    val customerId: Int? = null,
    @SerializedName("gig_id")
    val gigId: Int? = null,
    @SerializedName("arm_length")
    val armLength: Int? = null,
    @SerializedName("calf")
    val calf: Int? = null,
    @SerializedName("chest_circumference")
    val chestCircumference: Int? = null,
    @SerializedName("full_length")
    val fullLength: Int? = null,
    @SerializedName("hips_circumference")
    val hipsCircumference: Int? = null,
    @SerializedName("neck_circumference")
    val neckCircumference: Int? = null,
    @SerializedName("shoulder_breadth")
    val shoulderBreadth: Int? = null,
    @SerializedName("thigh")
    val thigh: Int? = null,
    @SerializedName("waist_circumference")
    val waistCircumference: Int? = null,
    @SerializedName("wrist_circumference")
    val wristCircumference: Int? = null
)