package com.tailorfit.android.tailorfitapp.measurement


import android.os.Bundle
import com.tailorfit.android.base.BaseViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tailorfit.android.Constants.Gender
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentGetMeasurementBinding
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import javax.inject.Inject


class GetMeasurementFragment : BaseViewModelFragment() {


    private lateinit var binding: FragmentGetMeasurementBinding

    private lateinit var viewModel: MeasurementViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetMeasurementBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MeasurementViewModel::class.java)
        val args = GetMeasurementFragmentArgs.fromBundle(arguments!!)

        when (args.gender) {
            Gender.MALE -> {
                viewModel.getMaleMeasurement(
                    prefsValueHelper.getAccessToken(),
                    prefsValueHelper.getGigId(),
                    prefsValueHelper.getCustomerId()
                )
            }

            Gender.FEMALE -> {
                viewModel.getFemaleMeasurement(
                    prefsValueHelper.getAccessToken(),
                    prefsValueHelper.getGigId(),
                    prefsValueHelper.getCustomerId()
                )
            }
        }

        showRecyclerView()
    }


    fun showRecyclerView() {

    }

    override fun getViewModel(): BaseViewModel = viewModel
}
