
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

    } else {
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

