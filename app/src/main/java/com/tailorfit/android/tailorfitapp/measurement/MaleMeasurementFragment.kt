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
import com.tailorfit.android.databinding.FragmentMeasurementBinding
import com.tailorfit.android.extensions.toDouble
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.MaleMeasurementRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import javax.inject.Inject


class MaleMeasurementFragment : BaseViewModelFragment() {

    private lateinit var binding: FragmentMeasurementBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var measurementViewModel: MeasurementViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeasurementBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        setUpToolbar()
        measurementViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MeasurementViewModel::class.java)

        binding.createMaleMeasurementButton.setOnClickListener {
//            if (validateTextLayouts(
//                    binding.armLengthEditText,
//                    binding.calfEditText,
//                    binding.chestCircumferenceEditText,
//                    binding.fullLengthEditText,
//                    binding.hipsCircumferenceEditText,
//                    binding.neckCircumferenceEditText,
//                    binding.shoulderBreadthEditText,
//                    binding.thighEditText,
//                    binding.waistCircumferenceEditText,
//                    binding.wristCircumferenceEditText
//                )
//            ) {
                measurementViewModel.createMaleMeasurement(
                    prefsValueHelper.getAccessToken(),
                    MaleMeasurementRequest(
                        binding.armLengthEditText.toDouble(),
                        binding.calfEditText.toDouble(),
                        binding.chestCircumferenceEditText.toDouble(),
                        prefsValueHelper.getCustomerId(),
                        binding.fullLengthEditText.toDouble(),
                        prefsValueHelper.getGigId(),
                        binding.hipsCircumferenceEditText.toDouble(),
                        binding.neckCircumferenceEditText.toDouble(),
                        binding.shoulderBreadthEditText.toDouble(),
                        binding.thighEditText.toDouble(),
                        prefsValueHelper.getUserId(),
                        binding.waistCircumferenceEditText.toDouble(),
                        binding.wristCircumferenceEditText.toDouble()
                    )
                )
                measurementViewModel.maleResponse.observe(viewLifecycleOwner, Observer {
                    if (it != null) {
                        findNavController().navigate(
                            MaleMeasurementFragmentDirections
                                .actionMaleMeasurementFragmentToDashBoardFragment()
                        )
                    }
                })

        }

    }

    private fun setUpToolbar() = mainActivity.run {
        setUpToolBar("", false)
        invalidateToolbarElevation(0)
    }

    override fun getViewModel(): BaseViewModel = measurementViewModel


}