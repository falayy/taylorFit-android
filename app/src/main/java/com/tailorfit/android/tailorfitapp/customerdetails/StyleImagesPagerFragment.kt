package com.tailorfit.android.tailorfitapp.customerdetails


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.databinding.FragmentStyleImagesPagerBinding

class StyleImagesPagerFragment : BaseFragment() {


    companion object {
        private const val IMAGE_LIST_KEY = "images"
        fun newInstance(image: String?): StyleImagesPagerFragment =
            StyleImagesPagerFragment().also {
                it.arguments = Bundle().apply { putString(IMAGE_LIST_KEY, image) }
            }
    }

    private lateinit var binding: FragmentStyleImagesPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStyleImagesPagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = arguments!!.getString(IMAGE_LIST_KEY)
        binding.styleImages.load(image)
    }

}
