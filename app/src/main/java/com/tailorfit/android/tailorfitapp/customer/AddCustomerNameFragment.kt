package com.tailorfit.android.tailorfitapp.customer


import com.tailorfit.android.R
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseCustomerFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.CustomerFormType


class AddCustomerNameFragment : BaseCustomerFormFragment() {


    override fun getCustomerFormType() = CustomerFormType.AddCustomerFragment

    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.customer_name_message)
        binding.editText.hint = getString(R.string.customer_hint_name)
    }

}
