package com.tailorfit.android.tailorfitapp.models.request


import com.google.gson.annotations.SerializedName

data class MaleMeasurementRequest(
    @SerializedName("arm_length")
    val armLength: Double = 0.0,
    @SerializedName("calf")
    val calf: Double = 0.0,
    @SerializedName("chest_circumference")
    val chestCircumference: Double = 0.0,
    @SerializedName("customer_id")
    val customerId: String? = null,
    @SerializedName("full_length")
    val fullLength: Double = 0.0,
    @SerializedName("gig_id")
    val gigId: String? = null,
    @SerializedName("hips_circumference")
    val hipsCircumference: Double = 0.0,
    @SerializedName("neck_circumference")
    val neckCircumference: Double = 0.0,
    @SerializedName("shoulder_breadth")
    val shoulderBreadth: Double = 0.0,
    @SerializedName("thigh")
    val thigh: Double = 0.0,
    @SerializedName("user_id")
    val userId: String? = null,
    @SerializedName("waist_circumference")
    val waistCircumference: Double = 0.0,
    @SerializedName("wrist_circumference")
    val wristCircumference: Double = 0.0
)