
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
