/**
 * Copyright (c) 2020 Falaye Iyanuoluwa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tailorfit.android.tailorfitapp.models.request

import com.google.gson.annotations.SerializedName

data class MeasurementRequest(
    @SerializedName("user_id")
    val userId: String? = null,
    @SerializedName("customer_id")
    val customerId: String? = null,
    @SerializedName("gig_id")
    val gigId: String? = null,
    @SerializedName("measurement")
    val measurement: Map<String, String>
)
