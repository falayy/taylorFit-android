package com.tailorfit.android.tailorfitapp.customer


import android.os.Bundle
import android.view.View
import com.tailorfit.android.R
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.tailorfitapp.baseforms.BaseCustomerFormFragment
import com.tailorfit.android.tailorfitapp.baseforms.CustomerFormType
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest


class AddCustomerFragment : BaseCustomerFormFragment() {

    private lateinit var customerRequest: CreateCustomerRequest

    override fun getCustomerFormType() = CustomerFormType.AddCustomerFragment

    override fun setDataHints(binding: FragmentBaseFormBinding) {
        binding.formDescription.text = getString(R.string.customer_name_message)
        binding.editText.hint = getString(R.string.customer_hint_name)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO get customerRequest

    }


    override fun getCustomerRequest(): CreateCustomerRequest = customerRequest

}
