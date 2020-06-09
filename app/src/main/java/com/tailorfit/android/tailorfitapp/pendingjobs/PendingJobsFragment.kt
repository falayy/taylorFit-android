package com.tailorfit.android.tailorfitapp.pendingjobs


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentPendingJobsBinding
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.userdashboard.DashBoardAdapter
import com.tailorfit.android.tailorfitapp.userdashboard.DashBoardFragmentDirections
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
            this.findNavController().navigate(
                DashBoardFragmentDirections.actionDashBoardFragmentToCustomerDetailsFragment(it!!)
            )
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

        dashBoardViewModel
            .pendingCustomerInfoResponse.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    binding.recyclerViewImage.apply {
                        visibility = View.VISIBLE
                        layoutManager = LinearLayoutManager(
                            context,
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                        adapter = dashBoardAdapter.apply {
                            submitList(it)
                        }
                    }
                }
            })
    }

    override fun getViewModel(): BaseViewModel = dashBoardViewModel


}
