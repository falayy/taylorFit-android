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
package com.tailorfit.android

import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.material.snackbar.Snackbar
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.base.LoadingCallback
import com.tailorfit.android.extensions.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.loading_indicator.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MainActivity : AppCompatActivity(), LoadingCallback {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var currentFragment: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        toolbar.overflowIcon = getDrawable(R.drawable.ic_more_vert_24dp)
        setSupportActionBar(toolbar)
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    fun setUpToolBar(
        toolbarTitle: String,
        isRootPage: Boolean = false,
        isDashBoard: Boolean = false
    ) {

        supportActionBar!!.run {
            setDisplayHomeAsUpEnabled(!isRootPage)
            var toolbarDrawable = if (!isRootPage && !isDashBoard) {
                R.drawable.ic_arrow_white_24dp
            } else if (isDashBoard) {
                R.drawable.store_drawable
            } else {
                0
            }
            setHomeAsUpIndicator(toolbarDrawable)
            toolbarTitleTextView.text = toolbarTitle
            val leftRightPaddingRes = if (isRootPage) R.dimen.toolbar_left_right_padding_root else
                R.dimen.toolbar_left_right_padding
            toolbarTitleTextView.setViewPadding(
                R.dimen.toolbar_top_bottom_padding,
                leftRightPaddingRes
            )
        }

    }

    fun setToolbarIcon(@DrawableRes resId: Int) {
        supportActionBar!!.setHomeAsUpIndicator(resId)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun showLoading() {
        showLoading(R.string.default_loading_message)
    }

    override fun showLoading(resId: Int) {
        showLoading(getString(resId))
    }

    override fun showLoading(message: String) {
        progressMessage.text = message
        if (isLoading()) return
        hideKeyBoard()
        loading_layout_container.showViewWithChildren()
        disableTouch()
    }

    override fun isLoading(): Boolean {
        return loading_layout_container.isVisible
    }

    override fun dismissLoading() {
        loading_layout_container.hide()
        enableTouch()
    }

    override fun showError(resId: Int) {
        showError(getString(resId))
    }

    fun invalidateToolbarElevation(scrollY: Int) {
        if (scrollY > (toolbar.measuredHeight / 2)) {
            appBarLayout.elevation = resources.getDimension(R.dimen.raised_toolbar_elevation)
        } else {
            appBarLayout.elevation = 0f
        }
    }

    override fun showError(message: String) {
        hideKeyBoard()
        dismissLoading()
        showMessageDialog(message)
    }

    fun showMessageDialog(message: String) {
        MaterialDialog(this).show {
            message(text = message)
            positiveButton(R.string.ok)
            cancelOnTouchOutside(false)
        }
    }

    fun showDialogWithAction(
        title: String?,
        body: String?,
        positiveRes: Int,
        positiveAction: (() -> Unit)?,
        negativeRes: Int?,
        negativeAction: (() -> Unit)?
    ) {
        MaterialDialog(this).show {
            if (title != null) title(text = title)
            if (body != null) message(text = body)
            if (negativeRes != null) negativeButton(negativeRes) { negativeAction?.invoke() }
            positiveButton(positiveRes) { positiveAction?.invoke() }
            cancelOnTouchOutside(false)
        }
    }

    fun showSnackBar(message: String) {
        hideKeyBoard()
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show()
    }

    fun setCurrentFragment(baseFragment: BaseFragment) {
        currentFragment = baseFragment
    }

    override fun onBackPressed() {
        // If the current fragment doesn't consume the back pressed action, then call super onBackPressed
        if (!currentFragment.onBackPressed()) super.onBackPressed()
    }
}
