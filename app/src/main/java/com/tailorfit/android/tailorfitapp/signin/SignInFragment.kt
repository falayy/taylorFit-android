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
package com.tailorfit.android.tailorfitapp.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentSignInBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.SignInRequest
import com.tailorfit.android.tailorfitapp.signin.NavigationFlow.*
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import javax.inject.Inject

class SignInFragment : BaseViewModelFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var viewModel: SignInViewModel

    private lateinit var binding: FragmentSignInBinding

    private var overrideNavigationFlow = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        arguments?.let {
            overrideNavigationFlow = SignInFragmentArgs.fromBundle(arguments!!).overrideFlow
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignInViewModel::class.java)
        binding.signUpText.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToSignUpFragment())
        }


        viewModel.navigationFlow.observe(viewLifecycleOwner, Observer {
            if (it == NEW_USER) {
                if (!overrideNavigationFlow) {
                    findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToDemoFragment())
                }
            }
        })

        viewModel.apply {

            phoneNumber.observe(viewLifecycleOwner, Observer {
                if (it != null) binding.phoneEditText.setText(it)
            })

            cleanUpObservables()
        }

        binding.signinButton.setOnClickListener {
            if (validateTextLayouts(
                    binding.phoneEditText,
                    binding.passwordEditText
                )
            ) {
                viewModel.signIn(
                    SignInRequest(
                        binding.passwordEditText.stringContent(),
                        binding.phoneEditText.stringContent()
                    )
                )
            } else {
                return@setOnClickListener
            }
        }

        viewModel.signInResponse.observe(this, Observer {
            if (it != null) {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToDashBoardFragment())
            }
        })
    }

    override fun getViewModel(): BaseViewModel = viewModel
}
