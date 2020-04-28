package com.tailorfit.android.tailorfitapp.measurement


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
import com.tailorfit.android.databinding.FragmentFemaleMeasurementBinding
import com.tailorfit.android.extensions.toInteger
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.FemaleMeasurementRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import javax.inject.Inject

class FemaleMeasurementFragment : BaseViewModelFragment() {

    private lateinit var binding: FragmentFemaleMeasurementBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var measurementViewModel: MeasurementViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFemaleMeasurementBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        measurementViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MeasurementViewModel::class.java)

        binding.createFemaleMeasurementButton.setOnClickListener {
            if (validateTextLayouts(
                    binding.armHoleEditText,
                    binding.armRoundEditText,
                    binding.bustLineEditText,
                    binding.bustRoundEditText,
                    binding.shoulderToShoulderEditText,
                    binding.fullLengthEditText,
                    binding.halfSleeveEditText,
                    binding.hipLineEditText,
                    binding.hipRoundEditText,
                    binding.naturalWaistLineEditText,
                    binding.sleeveLengthEditText,
                    binding.underBustEditText,
                    binding.naturalWaistRoundEditText
                )
            ) {
                measurementViewModel.createFemaleMeasurement(
                    prefsValueHelper.getAccessToken(),
                    FemaleMeasurementRequest(
                        binding.armHoleEditText.toInteger(),
                        binding.armRoundEditText.toInteger(),
                        binding.bustLineEditText.toInteger(),
                        binding.bustRoundEditText.toInteger(),
                        prefsValueHelper.getCustomerId(),
                        binding.fullLengthEditText.toInteger(),
                        prefsValueHelper.getGigId(),
                        binding.halfSleeveEditText.toInteger(),
                        binding.hipLineEditText.toInteger(),
                        binding.hipRoundEditText.toInteger(),
                        binding.naturalWaistLineEditText.toInteger(),
                        binding.naturalWaistRoundEditText.toInteger(),
                        binding.shoulderToShoulderEditText.toInteger(),
                        binding.sleeveLengthEditText.toInteger(),
                        binding.underBustEditText.toInteger(),
                        prefsValueHelper.getUserId()

                    )
                )
                measurementViewModel.femaleResponse.observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        findNavController().navigate(
                            FemaleMeasurementFragmentDirections
                                .actionFemaleMeasurementFragmentToDashBoardFragment()
                        )
                    }
                })
            }
        }
    }



    override fun getViewModel(): BaseViewModel = measurementViewModel


}
