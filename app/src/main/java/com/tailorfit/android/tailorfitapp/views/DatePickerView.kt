package com.tailorfit.android.tailorfitapp.views

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.tailorfit.android.utils.DateUtils
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DOBPickerView : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val calendar = Calendar.getInstance()
    private val dateFormat: DateFormat = SimpleDateFormat(
        DateUtils.FormatPattern.DISPLAY_DATE, Locale.getDefault()
    )

    companion object {
        private const val TAG = "DOBPickerView"
        fun <T> show(parentFragment: T) where T : Fragment, T : DOBPickerViewCallback {
            DOBPickerView().show(parentFragment.childFragmentManager, TAG)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = DatePickerDialog(
        activity!!,
        this,
        calendar[Calendar.YEAR],
        calendar[Calendar.MONTH],
        calendar[Calendar.DAY_OF_MONTH]
    ).apply { datePicker.maxDate = calendar.timeInMillis }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val callback = parentFragment as? DOBPickerViewCallback
            ?: throw IllegalArgumentException("Parent Fragment must implement DOBPickerViewCallback")
        val date = Calendar.getInstance().apply { set(year, month, dayOfMonth) }.time
        callback.onDOBPicked(dateFormat.format(date))
    }
}

interface DOBPickerViewCallback {
    fun onDOBPicked(formattedDate: String?)
}
