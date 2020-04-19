package com.tailorfit.android.tailorfitapp.signup


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseFragment
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
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignInFragment())
        }
        binding.signupButton.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToAddCustomerNameFragment())
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
                        binding.phoneEditText.stringContent().toLong() // TODO. Why Long??. Collect phone number as String 🤨
                    )
                )
            }
        }

        viewModel.signUpResponse.observe(this, Observer {
            if (it != null) {
//                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToAddCustomerFragment())
            }
        })
    }

    override fun getViewModel(): BaseViewModel = viewModel
}
