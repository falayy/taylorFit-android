/**
 * Copyright (c) 2019 Cotta & Cush Limited.
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
        const val USER_DATA = "USER_DATA"
        const val AUTH_FLOW_PHONE_NUMBER = "AUTH_FLOW_PHONE_NUMBER"
        const val VERIFIED_PHONE_NUMBER = "VERIFIED_PHONE_NUMBER"
        const val DEMO_SHOWN = "DEMO_SHOWN"
    }

    fun setAccessToken(accessToken: String) = prefsUtils.putString(ACCESS_TOKEN, accessToken)

    fun getAccessToken() = prefsUtils.getString(ACCESS_TOKEN, null)

    fun setDemoShownStatus(isDemoShown: Boolean) = prefsUtils.putBoolean(DEMO_SHOWN, isDemoShown)

    fun isDemoShown() = prefsUtils.getBoolean(DEMO_SHOWN, false)

    fun setLastVerifiedPhoneNumber(number: String) = prefsUtils.putString(VERIFIED_PHONE_NUMBER, number)

    fun getLastVerifiedPhoneNumber() = prefsUtils.getString(VERIFIED_PHONE_NUMBER, null)
}
