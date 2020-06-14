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
package com.tailorfit.android.tailorfitapp

import com.tailorfit.android.utils.PrefsUtils
import javax.inject.Inject

class PrefsValueHelper @Inject constructor(private val prefsUtils: PrefsUtils) {

    companion object {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val USER_ID = "USER_ID"
        const val CUSTOMER_NAME = "CUSTOMER_NAME"
        const val CUSTOMER_PHONE = "CUSTOMER_PHONE"
        const val CUSTOMER_ID = "CUSTOMER_ID"
        const val GIG_ID = "GIG_ID"
        const val GIG_TITLE = "GIG_TITLE"
        const val GIG_STYLE_NAME = "GIG_STYLE_NAME"
        const val GIG_DUE_DATE = "GIG_DUE_DATE"
        const val GIG_PRICE = "GIG_PRICE"
    }

    fun setAccessToken(accessToken: String) = prefsUtils.putString(ACCESS_TOKEN, accessToken)

    fun getAccessToken() = prefsUtils.getString(ACCESS_TOKEN, null)

    fun setUserId(userId: String) = prefsUtils.putString(USER_ID, userId)

    fun getUserId() = prefsUtils.getString(USER_ID, "")

    fun setCustomerName(name: String) = prefsUtils.putString(CUSTOMER_NAME, name)

    fun getCustomerName() = prefsUtils.getString(CUSTOMER_NAME, "")

    fun setCustomerPhone(phone: String) = prefsUtils.putString(CUSTOMER_PHONE, phone)

    fun getCustomerPhone() = prefsUtils.getString(CUSTOMER_PHONE, "")

    fun setCustomerId(customerId: String) = prefsUtils.putString(CUSTOMER_ID, customerId)

    fun getCustomerId() = prefsUtils.getString(CUSTOMER_ID, "")

    fun getGigId() = prefsUtils.getString(GIG_ID, "")

    fun setGigId(gigId: String) = prefsUtils.putString(GIG_ID, gigId)

    fun setGigTitle(gigTitle: String) = prefsUtils.putString(GIG_TITLE, gigTitle)

    fun getGigTitle() = prefsUtils.getString(GIG_TITLE, "")

    fun setGigStyleName(gigStyleName: String) = prefsUtils.putString(GIG_STYLE_NAME, gigStyleName)

    fun getGigStyleName() = prefsUtils.getString(GIG_STYLE_NAME, "")

    fun setGigDueDate(gigDueDate: String) = prefsUtils.putString(GIG_DUE_DATE, gigDueDate)

    fun getGigDueDate() = prefsUtils.getString(GIG_DUE_DATE, "")

    fun setGigPrice(gigPrice: String) = prefsUtils.putString(GIG_PRICE, gigPrice)

    fun getGigPrice() = prefsUtils.getString(GIG_PRICE, "")
}
