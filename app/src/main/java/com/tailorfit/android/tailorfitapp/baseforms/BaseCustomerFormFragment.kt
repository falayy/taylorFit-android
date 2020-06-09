package com.tailorfit.android.tailorfitapp.baseforms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.customer.*
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import javax.inject.Inject

enum class CustomerFormType {
    AddCustomerFragment,
    AddCustomerGenderFragment,
    AddCustomerPhoneFragment
}

abstract class BaseCustomerFormFragment : BaseViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var binding: FragmentBaseFormBinding
    private lateinit var createCustomerRequest: CreateCustomerRequest


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        setUpDaggerViewModel()
        navigate()
    }

    private fun navigate() {
        setDataHints(binding)
        createCustomerRequest = CreateCustomerRequest()
        when (getCustomerFormType()) {
            CustomerFormType.AddCustomerFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        val name = binding.editText.stringContent()
//                        prefsValueHelper.setCustomerName(name)
                        createCustomerRequest.name = name
                        findNavController().navigate(
                            AddCustomerNameFragmentDirections.
                                actionAddCustomerNameFragmentToAddCustomerPhoneFragment(createCustomerRequest)
                        )
                    } else {
                        return@setOnClickListener
                    }
                }
            }
            CustomerFormType.AddCustomerPhoneFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        val phoneNumber = binding.editText.stringContent()
//                        prefsValueHelper.setCustomerPhone(phoneNumber)
                        var args = AddCustomerPhoneFragmentArgs.fromBundle(arguments!!)
                        createCustomerRequest.phoneNumber = phoneNumber
                        createCustomerRequest.name = args.createCustomer.name
                        findNavController().navigate(
                            AddCustomerPhoneFragmentDirections.actionAddCustomerPhoneFragmentToAddCustomerGenderFragment(createCustomerRequest)
                        )
                    } else {
                        return@setOnClickListener
                    }
                }
            }
            CustomerFormType.AddCustomerGenderFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        var gender = binding.editText.stringContent()
                        val args = AddCustomerGenderFragmentArgs.fromBundle(arguments!!)
                        createCustomerRequest.gender =  gender
                        createCustomerRequest.phoneNumber = args.createCustomer.phoneNumber
                        createCustomerRequest.name = args.createCustomer.name
                        createCustomerRequest.userId = prefsValueHelper.getUserId()
                        createCustomer()
                    } else {
                        return@setOnClickListener
                    }
                }
            }
        }
    }

    private fun setUpToolbar() = mainActivity.run {
        setUpToolBar("", false)
        invalidateToolbarElevation(0)
    }

    private fun createCustomer() {
        setUpDaggerViewModel().createCustomer(
            prefsValueHelper.getAccessToken().toString(),
            createCustomerRequest
        )
        setUpDaggerViewModel().createCustomerResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(AddCustomerGenderFragmentDirections.actionAddCustomerGenderFragmentToAddGigTitleFragment())
            }
        })
    }


    protected abstract fun getCustomerFormType(): CustomerFormType

    protected abstract fun setDataHints(binding: FragmentBaseFormBinding)

    protected abstract fun setUpDaggerViewModel(): AddCustomerViewModel


}

