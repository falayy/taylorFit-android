package com.tailorfit.android.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatDialogFragment
import com.tailorfit.android.R
import java.text.SimpleDateFormat
import java.util.*

class DatePickerFragment : AppCompatDialogFragment(),  DatePickerDialog.OnDateSetListener {
    private val c = Calendar.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Set the current date as the default date
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Return a new instance of DatePickerDialog
        val datePickerDialog =
            DatePickerDialog(context!!, this@DatePickerFragment, year, month, day)
        datePickerDialog.show()
        datePickerDialog.apply {
            getButton(DatePickerDialog.BUTTON_POSITIVE)
                .setTextColor(resources.getColor(R.color.primaryColorChocolate))
            getButton(DatePickerDialog.BUTTON_NEGATIVE)
                .setTextColor(resources.getColor(R.color.primaryColorChocolate))
        }
        return datePickerDialog
    }

    // called when a date has been selected
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, day)
        val selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(c.getTime())

        // send date back to the target fragment
        targetFragment!!.onActivityResult(
            targetRequestCode,
            Activity.RESULT_OK,
            Intent().putExtra("selectedDate", selectedDate)
        )
    }

}