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
import com.tailorfit.android.databinding.FragmentMaleMeasurementBinding
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.MeasurementRequest
import javax.inject.Inject


class MaleMeasurementFragment : BaseViewModelFragment() {

    private lateinit var binding: FragmentMaleMeasurementBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var measurementViewModel: MeasurementViewModel
    private val map = HashMap<String, String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMaleMeasurementBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        setUpToolbar()
        measurementViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MeasurementViewModel::class.java)

        binding.createMaleMeasurementButton.setOnClickListener {

            map[binding.armLengthTextInputLayout.hint.toString()] =
                binding.armLengthEditText.text.toString()
            map[binding.calfTextInputLayout.hint.toString()] =
                binding.calfEditText.text.toString()
            map[binding.chestCircumferenceTextInputLayout.hint.toString()] =
                binding.chestCircumferenceEditText.text.toString()
            map[binding.fullLengthTextInputLayout.hint.toString()] =
                binding.fullLengthEditText.text.toString()
            map[binding.hipsCircumferenceTextInputLayout.hint.toString()] =
                binding.hipsCircumferenceEditText.text.toString()
            map[binding.neckCircumferenceTextInputLayout.hint.toString()] =
                binding.neckCircumferenceEditText.text.toString()
            map[binding.shoulderBreadthTextInputLayout.hint.toString()] =
                binding.shoulderBreadthEditText.text.toString()
            map[binding.thighTextInputLayout.hint.toString()] =
                binding.thighEditText.text.toString()
            map[binding.waistCircumferenceTextInputLayout.hint.toString()] =
                binding.waistCircumferenceEditText.text.toString()
            map[binding.wristCircumferenceTextInputLayout.hint.toString()] =
                binding.wristCircumferenceEditText.text.toString()

            measurementViewModel.createMeasurement(
                prefsValueHelper.getAccessToken(),
                MeasurementRequest(
                    prefsValueHelper.getUserId(),
                    prefsValueHelper.getCustomerId(),
                    prefsValueHelper.getGigId(),
                    map
                )
            )

            measurementViewModel.createMeasurementResponse.observe(viewLifecycleOwner, Observer {
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