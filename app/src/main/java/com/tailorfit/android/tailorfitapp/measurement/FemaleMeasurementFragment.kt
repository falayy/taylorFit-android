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
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.MeasurementRequest
import javax.inject.Inject

class FemaleMeasurementFragment : BaseViewModelFragment() {

    private lateinit var binding: FragmentFemaleMeasurementBinding

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
        binding = FragmentFemaleMeasurementBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        setUpToolbar()
        measurementViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MeasurementViewModel::class.java)

        binding.createFemaleMeasurementButton.setOnClickListener {
            map[binding.armHoleTextInputLayout.hint.toString()] =
                binding.armHoleEditText.text.toString()
            map[binding.armRoundTextInputLayout.hint.toString()] =
                binding.armRoundEditText.text.toString()
            map[binding.bustLineTextInputLayout.hint.toString()] =
                binding.bustLineEditText.text.toString()
            map[binding.bustRoundTextInputLayout.hint.toString()] =
                binding.bustRoundEditText.text.toString()
            map[binding.halfSleeveTextInputLayout.hint.toString()] =
                binding.halfSleeveEditText.text.toString()
            map[binding.fullLengthTextInputLayout.hint.toString()] =
                binding.fullLengthEditText.text.toString()
            map[binding.hipLineTextInputLayout.hint.toString()] =
                binding.hipLineEditText.text.toString()
            map[binding.hipRoundTextInputLayout.hint.toString()] =
                binding.hipRoundEditText.text.toString()
            map[binding.naturalWaistTextInputLayout.hint.toString()] =
                binding.naturalWaistLineEditText.text.toString()
            map[binding.naturalWaistRoundTextInputLayout.hint.toString()] =
                binding.naturalWaistRoundEditText.text.toString()
            map[binding.shoulderToShoulderTextInputLayout.hint.toString()] =
                binding.shoulderToShoulderEditText.text.toString()
            map[binding.sleeveLengthTextInputLayout.hint.toString()] =
                binding.sleeveLengthEditText.text.toString()
            map[binding.underBustTextInputLayout.hint.toString()] =
                binding.underBustEditText.text.toString()
            measurementViewModel.createMeasurement(
                prefsValueHelper.getAccessToken(),
                MeasurementRequest(
                    prefsValueHelper.getUserId(),
                    prefsValueHelper.getCustomerId(),
                    prefsValueHelper.getGigId(),
                    map
                )
            )
            measurementViewModel.createMeasurementResponse
                .observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    findNavController().navigate(
                        FemaleMeasurementFragmentDirections
                            .actionFemaleMeasurementFragmentToDashBoardFragment()
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
