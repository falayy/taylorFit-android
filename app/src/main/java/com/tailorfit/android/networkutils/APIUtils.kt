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
package com.tailorfit.android.networkutils

import org.json.JSONObject
import retrofit2.Response
import timber.log.Timber

const val GENERIC_ERROR_MESSAGE = "We are unable to proceed due to network failure. Please try again"
const val GENERIC_ERROR_CODE = "-1"

fun <T : Any> getAPIResult(response: Response<BaseAPIResponse<T>>): Result<T> {
    if (response.isSuccessful) {
        val body = response.body()
        if (body?.data != null) {
            return Result.Success(body.data!!)
        }
    }

    else {
        val errorBody = response.errorBody()
        if (errorBody != null) {
            val errorBodyString = errorBody.string()
            return Result.Error(
                getErrorCode(errorBodyString), getErrorMessage(errorBodyString)
            )
        }
    }
    // Fallback to regular status code and message
    return Result.Error("${response.code()}", response.message())
}

fun getErrorMessage(errorBody: String): String {
    return try {
        val errorBodyJsonObject = JSONObject(errorBody)
        errorBodyJsonObject.getString("message")
    } catch (e: Exception) {
        Timber.e(e)
        GENERIC_ERROR_MESSAGE
    }
}

fun getErrorCode(errorBody: String): String {
    return try {
        val errorBodyJsonObject = JSONObject(errorBody)
        errorBodyJsonObject.getString("code")
    } catch (e: Exception) {
        Timber.e(e)
        GENERIC_ERROR_CODE
    }
}
