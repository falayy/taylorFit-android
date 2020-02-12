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
package com.tailorfit.android.base

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.tailorfit.android.App
import com.tailorfit.android.MainActivity
import com.tailorfit.android.R
import com.tailorfit.android.di.AppComponent

abstract class BaseFragment : Fragment() {

    protected val mainActivity: MainActivity
        get() {
            return activity as? MainActivity ?: throw IllegalStateException("Not attached!")
        }

    protected val daggerAppComponent: AppComponent
        get() = (mainActivity.applicationContext as App).component

    override fun onStart() {
        super.onStart()
        mainActivity.setCurrentFragment(this)
    }

    fun showMessageDialog(message: String) = mainActivity.showMessageDialog(message)

    fun showDialogWithAction(
        title: String? = null,
        body: String? = null,
        @StringRes positiveRes: Int = R.string.ok,
        positiveAction: (() -> Unit)? = null,
        @StringRes negativeRes: Int? = null,
        negativeAction: (() -> Unit)? = null
    ) = mainActivity.showDialogWithAction(title, body, positiveRes, positiveAction, negativeRes, negativeAction)

    fun showSnackBar(@StringRes stringRes: Int) = mainActivity.showSnackBar(getString(stringRes))

    fun showSnackBar(message: String) = mainActivity.showSnackBar(message)

    // Return true if you handle the back press in your fragment
    open fun onBackPressed(): Boolean = false
}
