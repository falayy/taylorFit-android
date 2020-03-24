package com.tailorfit.android.tailorfitapp.gig


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentAddGigDetailsBinding
import javax.inject.Inject


class AddGigDetailsFragment : BaseViewModelFragment() {

    private lateinit var binding : FragmentAddGigDetailsBinding
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel :  GigViewModel
    private val addGigImageDetailsAdapter : AddGigImageDetailsAdapter by lazy {
        AddGigImageDetailsAdapter(AddGigImageDetailsAdapter.OnclickListener{
            chooseImages()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAddGigDetailsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GigViewModel::class.java)
        viewModel.getImagePlaceHolders()
        getImagePlaceHolder()
    }

    private fun getImagePlaceHolder() {
        viewModel.imagePlaceHolder.observe(this, Observer {
            Log.d("TAG", "size in fragment  ${it.size}")
            binding.recyclerViewImage.apply {
                layoutManager = LinearLayoutManager(
                    context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = addGigImageDetailsAdapter.apply {
                    submitList(it)
                }
            }
        })
    }

    private fun chooseImages() {
        Toast.makeText(context, "choose the images", Toast.LENGTH_LONG).show()
    }

    override fun getViewModel(): BaseViewModel = viewModel

}
