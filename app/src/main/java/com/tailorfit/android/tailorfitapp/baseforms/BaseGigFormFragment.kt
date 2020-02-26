package com.tailorfit.android.tailorfitapp.baseforms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.gig.AddGigStyleFragmentDirections
import com.tailorfit.android.tailorfitapp.gig.AddGigTitleFragmentDirections
import com.tailorfit.android.tailorfitapp.models.request.CreateGigRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import com.tailorfit.android.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_base_form.*

enum class GigFormType {
    AddGigDueDateFragment,
    AddGigStyleFragment,
    AddGigTitleFragment,
    AddGigPriceFragment
}


abstract class BaseGigFormFragment : BaseFragment() {

    private lateinit var binding: FragmentBaseFormBinding
    private var data = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (validateTextLayouts(binding.editText)) {
            data = binding.editText.stringContent()
            navigate()
        }
    }

    private fun navigate() {
        when (getGigFormType()) {
            GigFormType.AddGigTitleFragment -> {
                getGigRequest().title = data
                findNavController().navigate(AddGigTitleFragmentDirections.actionAddGigTitleFragmentToAddGigStyleFragment())
            }
            GigFormType.AddGigStyleFragment -> {
                getGigRequest().styleName = data
                findNavController().navigate(AddGigStyleFragmentDirections.actionAddGigStyleFragmentToAddGigDueDateFragment())
            }
            GigFormType.AddGigDueDateFragment -> {
                getGigRequest().date = DateUtils.formatDateToSQLDate(data)!!
            }
        }
    }


    protected abstract fun getGigRequest(): CreateGigRequest

    protected abstract fun getGigFormType(): GigFormType

    protected abstract fun setDataHints(binding: FragmentBaseFormBinding)


}

