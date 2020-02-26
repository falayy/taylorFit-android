package com.tailorfit.android.tailorfitapp.customer


import com.tailorfit.android.R
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseCustomerFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.CustomerFormType
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest


class AddCustomerPhoneFragment : BaseCustomerFormFragment() {



    override fun getCustomerRequest(): CreateCustomerRequest {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCustomerFormType(): CustomerFormType {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.customer_number_message)
        binding.editText.hint = getString(R.string.customer_number_hint)
    }


}