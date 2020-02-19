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
