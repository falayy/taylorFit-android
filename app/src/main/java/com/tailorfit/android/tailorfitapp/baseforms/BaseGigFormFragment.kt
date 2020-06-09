package com.tailorfit.android.tailorfitapp.baseforms

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentBaseFormBinding
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.gig.*
import com.tailorfit.android.tailorfitapp.models.request.CreateGigRequest
import com.tailorfit.android.tailorfitapp.validateTextLayouts
import com.tailorfit.android.utils.DatePickerFragment
import com.tailorfit.android.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_base_form.*
import javax.inject.Inject

enum class GigFormType {
    AddGigDueDateFragment,
    AddGigStyleFragment,
    AddGigTitleFragment,
    AddGigPriceFragment
}


abstract class BaseGigFormFragment : BaseViewModelFragment() {

    private lateinit var binding: FragmentBaseFormBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var viewModel: GigViewModel

    private lateinit var createGigRequest: CreateGigRequest

    private var dateString = ""
    private var dob = ""
    private val REQUEST_CODE = 11


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseFormBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        setUpToolbar()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GigViewModel::class.java)
        setDataHints(binding)
        navigate()
    }

    private fun navigate() {
        createGigRequest = CreateGigRequest()
        when (getGigFormType()) {
            GigFormType.AddGigTitleFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        val title = binding.editText.stringContent()
                        prefsValueHelper.setGigTitle(title)
                        createGigRequest.title = title
                        findNavController().navigate(
                            AddGigTitleFragmentDirections.actionAddGigTitleFragmentToAddGigStyleFragment(
                                createGigRequest
                            )
                        )
                    } else {
                        return@setOnClickListener
                    }
                }
            }
            GigFormType.AddGigStyleFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        val style = binding.editText.stringContent()
                        val args = AddGigStyleFragmentArgs.fromBundle(arguments!!)
                        createGigRequest.title = args.createGig.title
                        createGigRequest.styleName = style
                        prefsValueHelper.setGigStyleName(style)
                        findNavController().navigate(
                            AddGigStyleFragmentDirections.actionAddGigStyleFragmentToAddGigDueDateFragment(
                                createGigRequest
                            )
                        )
                    } else {
                        return@setOnClickListener
                    }
                }
            }
            GigFormType.AddGigDueDateFragment -> {
                binding.editText.setOnClickListener {
                    val newFragment = DatePickerFragment()
                    newFragment.setTargetFragment(this, REQUEST_CODE)
                    newFragment.show(fragmentManager!!, "datePicker")
                }
                val args = AddGigDueDateFragmentArgs.fromBundle(arguments!!)
                createGigRequest.date = dob
                createGigRequest.title = args.createGig.title
                createGigRequest.styleName = args.createGig.styleName
                Log.d("TAG", "date logged ${createGigRequest.date} $dob")
                binding.FormProceedButton.setOnClickListener {
                    findNavController().navigate(
                        AddGigDueDateFragmentDirections.actionAddGigDueDateFragmentToAddGigPriceFragment(
                            createGigRequest
                        )
                    )
                }
            }
            GigFormType.AddGigPriceFragment -> {
                binding.FormProceedButton.setOnClickListener {
                    if (validateTextLayouts(binding.editText)) {
                        val price = binding.editText.stringContent()
                        val args = AddGigPriceFragmentArgs.fromBundle(arguments!!)
                        createGigRequest.date = args.createGig.date
                        createGigRequest.title = args.createGig.title
                        createGigRequest.styleName = args.createGig.styleName
                        createGigRequest.price = price
                        findNavController().navigate(
                            AddGigPriceFragmentDirections.actionAddGigPriceFragmentToAddGigDetails(
                                createGigRequest
                            )
                        )
                    } else {
                        return@setOnClickListener
                    }
                }
            }
        }
    }

    private fun setUpToolbar() = mainActivity.run {
        setUpToolBar("", false)
        invalidateToolbarElevation(0)
    }

    protected abstract fun getGigFormType(): GigFormType

    protected abstract fun setDataHints(binding: FragmentBaseFormBinding)

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            dateString = data!!.getStringExtra("selectedDate")!!
            dob = dateString
            binding.editText.setText(DateUtils.formatDateToDisplayDate(dateString))
        }

    }

    override fun getViewModel(): BaseViewModel = viewModel

}

