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
package com.tailorfit.android.tailorfitapp.signup

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
import com.tailorfit.android.databinding.FragmentSignUpBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.models.request.SignUpRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import javax.inject.Inject

class SignUpFragment : BaseViewModelFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SignUpViewModel

    private lateinit var binding: FragmentSignUpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignUpViewModel::class.java)

        binding.signInText.setOnClickListener {
            findNavController().navigate(
                SignUpFragmentDirections.actionSignUpFragmentToSignInFragment(
                    true
                )
            )
        }
        binding.signupButton.setOnClickListener {
            if (validateTextLayouts(
                    binding.nameEditText,
                    binding.phoneEditText,
                    binding.storeEditText,
                    binding.passwordEditText
                )
            ) {
                viewModel.signUp(
                    SignUpRequest(
                        binding.storeEditText.stringContent(),
                        binding.nameEditText.stringContent(),
                        binding.passwordEditText.stringContent(),
                        binding.phoneEditText.stringContent()
                    )
                )
            }
        }

        viewModel.apply {
            signUpResponse.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToDashBoardFragment())
                }
            })
            cleanUpObservables()
        }
    }

    override fun getViewModel(): BaseViewModel = viewModel
}
