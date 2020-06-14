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
package com.tailorfit.android.extensions

import android.text.InputFilter
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.DimenRes
import androidx.core.view.children
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

fun View.show() {
    visibility = VISIBLE
}

fun View.hide() {
    visibility = GONE
}

fun ViewGroup.showViewWithChildren() {
    show()
    for (view in children) {
        view.show()
    }
}

fun RecyclerView.onScrollChanged(scrollListener: (Int) -> Unit) =
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(
            recyclerView: RecyclerView,
            dx: Int,
            dy: Int
        ) {
            super.onScrolled(recyclerView, dx, dy)
            scrollListener(computeVerticalScrollOffset())
        }
    })

fun NestedScrollView.onScrollChanged(scrollListener: (Int) -> Unit) =
    setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
        scrollListener(scrollY)
    })

fun ViewPager2.onPageChanged(listener: (Int) -> Unit) {
    registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            listener(position)
        }
    })
}

fun View.setViewPadding(@DimenRes topBottomPaddingRes: Int, @DimenRes leftRightPaddingRes: Int) {
    val leftRightPadding = context.resources.getDimension(leftRightPaddingRes).toInt()
    val topBottomPadding = context.resources.getDimension(topBottomPaddingRes).toInt()
    setPadding(leftRightPadding, topBottomPadding, leftRightPadding, topBottomPadding)
}

fun EditText.setMaxLength(length: Int) {
    this.filters = arrayOf(InputFilter.LengthFilter(length))
}
