package com.tailorfit.android.tailorfitapp.baseforms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.customer.*
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import kotlinx.android.synthetic.main.fragment_base_form.*
import timber.log.Timber
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
    private var data = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDataHints(binding)
        setUpDaggerViewModel()
        navigate()
    }

    private fun navigate() {
        var name = ""
        var phoneNumber = ""
        when (getCustomerFormType()) {
            CustomerFormType.AddCustomerFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        name = binding.editText.stringContent()
                        findNavController().navigate(
                            AddCustomerNameFragmentDirections.actionAddCustomerNameFragmentToAddCustomerPhoneFragment()
                        )
                    } else {
                        return@setOnClickListener
                    }
                }
            }
            CustomerFormType.AddCustomerPhoneFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        phoneNumber = binding.editText.stringContent()
                        findNavController().navigate(
                            AddCustomerPhoneFragmentDirections.actionAddCustomerPhoneFragmentToAddCustomerGenderFragment()
                        )
                    } else {
                        return@setOnClickListener
                    }
                }
            }
            CustomerFormType.AddCustomerGenderFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        data = binding.editText.stringContent()
                        //TODO name and phoneNumber not populated cos of when statement
                        createCustomerRequest = CreateCustomerRequest(
                            data,
                            name,
                            phoneNumber,
                            prefsValueHelper.getUserId()
                        )
                        createCustomer()
                    } else {
                        return@setOnClickListener
                    }
                }
            }
        }
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

