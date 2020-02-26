package com.tailorfit.android.tailorfitapp.gig

import com.tailorfit.android.R
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseGigFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.GigFormType
import com.tailorfit.android.tailorfitapp.models.request.CreateGigRequest
import com.tailorfit.android.tailorfitapp.views.DOBPickerViewCallback
import kotlinx.android.synthetic.main.fragment_base_form.*


class AddGigDueDateFragment : BaseGigFormFragment() , DOBPickerViewCallback {
    private lateinit var createGigRequest: CreateGigRequest

    override fun getGigRequest(): CreateGigRequest = createGigRequest

    override fun getGigFormType(): GigFormType = GigFormType.AddGigDueDateFragment


    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.gig_due_date_message)
        binding.editText.hint = getString(R.string.gig_due_date_hint)
    }

    override fun onDOBPicked(formattedDate: String?) {
        edit_text.setText(formattedDate)
        edit_text.error = null
    }

}