package com.tailorfit.android.tailorfitapp.models.request


import com.google.gson.annotations.SerializedName

data class MaleMeasurementRequest(
    @SerializedName("arm_length")
    val armLength: Int? = null,
    @SerializedName("calf")
    val calf: Int? = null,
    @SerializedName("chest_circumference")
    val chestCircumference: Int? = null,
    @SerializedName("customer_id")
    val customerId: String? = null,
    @SerializedName("full_length")
    val fullLength: Int? = null,
    @SerializedName("gig_id")
    val gigId: String? = null,
    @SerializedName("hips_circumference")
    val hipsCircumference: Int? = null,
    @SerializedName("neck_circumference")
    val neckCircumference: Int? = null,
    @SerializedName("shoulder_breadth")
    val shoulderBreadth: Int? = null,
    @SerializedName("thigh")
    val thigh: Int? = null,
    @SerializedName("user_id")
    val userId: String? = null,
    @SerializedName("waist_circumference")
    val waistCircumference: Int? = null,
    @SerializedName("wrist_circumference")
    val wristCircumference: Int? = null
)