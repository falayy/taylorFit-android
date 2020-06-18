package com.tailorfit.android.tailorfitapp.models.local

import com.google.gson.annotations.SerializedName

data class KeyValue(
    @SerializedName("name")
    val name: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("id")
    val id: String
) {
    override fun toString(): String = name
}

data class LOVResponse(
    @SerializedName("genders")
    val genders: List<KeyValue>,
    @SerializedName("professions")
    val professions: List<KeyValue>,
    @SerializedName("marital_statuses")
    val maritalStatuses: List<KeyValue>
)
