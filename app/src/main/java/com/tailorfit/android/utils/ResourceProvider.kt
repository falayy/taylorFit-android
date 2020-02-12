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
package com.tailorfit.android.utils

import android.app.Application
import javax.inject.Inject

/**
 * Disclaimer - This class intended to be used by Viewmodels to provide common resources (Strings, ints, colors etc)
 * The class will require the app to be restarted for a locale or configuration change to reflect.
 * The class can not provide Activity scoped theme/styles related resources.
 *
 * @param app the application context to use.
 */
class ResourceProvider @Inject constructor(private val app: Application) {

    fun getString(resId: Int): String {
        return app.getString(resId)
    }

    fun getString(resId: Int, value: String): String {
        return app.getString(resId, value)
    }
}
