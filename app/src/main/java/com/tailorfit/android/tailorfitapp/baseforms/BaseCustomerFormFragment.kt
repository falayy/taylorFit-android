/**
 * Copyright (c) 2020 Falaye Iyanuoluwa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tailorfit.android.tailorfitapp.baseforms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.customer.*
import com.tailorfit.android.tailorfitapp.models.request.CreateCustomerRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import javax.inject.Inject

enum class CustomerFormType {
    AddCustomerFragment,
    AddCustomerPhoneFragment
}

abstract class BaseCustomerFormFragment : BaseViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentBaseFormBinding
    private lateinit var createCustomerRequest: CreateCustomerRequest

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
                        createCustomerRequest.name = name
                        findNavController().navigate(
                            AddCustomerNameFragmentDirections
                                .actionAddCustomerNameFragmentToAddCustomerPhoneFragment(
                                    createCustomerRequest
                                )
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
                        var args = AddCustomerPhoneFragmentArgs.fromBundle(arguments!!)
                        createCustomerRequest.phoneNumber = phoneNumber
                        createCustomerRequest.name = args.createCustomer.name
                        findNavController().navigate(
                            AddCustomerPhoneFragmentDirections.actionAddCustomerPhoneFragmentToAddCustomerGenderFragment(
                                createCustomerRequest
                            )
                        )
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



    protected abstract fun getCustomerFormType(): CustomerFormType

    protected abstract fun setDataHints(binding: FragmentBaseFormBinding)

    protected abstract fun setUpDaggerViewModel(): AddCustomerViewModel
}
