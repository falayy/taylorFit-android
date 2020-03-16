package com.tailorfit.android.tailorfitapp.gig

import com.tailorfit.android.R
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseGigFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.GigFormType


class AddGigDueDateFragment : BaseGigFormFragment()  {


    override fun getGigFormType(): GigFormType = GigFormType.AddGigDueDateFragment


    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.gig_due_date_message)
        binding.editText.hint = getString(R.string.gig_due_date_hint)
    }

}