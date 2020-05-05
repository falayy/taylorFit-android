package com.tailorfit.android.tailorfitapp.pendingjobs


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.tailorfitapp.completedjobs.CompletedJobsFragmentDirections
import com.tailorfit.android.databinding.FragmentPendingJobsBinding
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.userdashboard.DashBoardAdapter
import com.tailorfit.android.tailorfitapp.userdashboard.DashBoardViewModel
import javax.inject.Inject


class PendingJobsFragment : BaseViewModelFragment() {

    private lateinit var binding: FragmentPendingJobsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var dashBoardViewModel: DashBoardViewModel

    private val dashBoardAdapter by lazy {
        DashBoardAdapter(DashBoardAdapter.OnclickListener {
            findNavController().navigate(CompletedJobsFragmentDirections.actionCompletedJobsFragmentToCustomerDetailsFragment(it))
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPendingJobsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        dashBoardViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DashBoardViewModel::class.java)
        dashBoardViewModel.getCustomerPendingJobsInfo(
            prefsValueHelper.getAccessToken(),
            prefsValueHelper.getUserId()
        )

        binding.recyclerViewImage.adapter = dashBoardAdapter


    }

    override fun getViewModel(): BaseViewModel = dashBoardViewModel


}
