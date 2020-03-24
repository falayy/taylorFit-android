package com.tailorfit.android.tailorfitapp.customer


import android.text.InputType
import com.tailorfit.android.R
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseCustomerFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.CustomerFormType
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest


class AddCustomerPhoneFragment : BaseCustomerFormFragment() {

    override fun getCustomerFormType() = CustomerFormType.AddCustomerPhoneFragment

    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.customer_number_message)
        binding.editText.hint = getString(R.string.customer_number_hint)
        binding.editText.inputType = InputType.TYPE_CLASS_NUMBER

    }


}