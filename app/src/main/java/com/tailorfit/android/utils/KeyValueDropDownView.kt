package com.tailorfit.android.utils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import com.tailorfit.android.tailorfitapp.models.local.KeyValue

class KeyValueDropDownView(context: Context, attrs: AttributeSet?) : AppCompatAutoCompleteTextView(context, attrs) {

    private var selectedPosition = -1

    override fun onFinishInflate() {
        super.onFinishInflate()
        setOnItemClickListener { _, _, position, _ ->
            selectedPosition = position
            if (isSelectionValid()) error = null
        }
    }

    fun getSelectedItem() = adapter.getItem(selectedPosition) as KeyValue

    fun getSelectedItemKey() = getSelectedItem().key

    fun isSelectionValid() = selectedPosition >= 0
}
