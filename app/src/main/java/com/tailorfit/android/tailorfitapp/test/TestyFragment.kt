package com.tailorfit.android.tailorfitapp.test


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.tailorfit.android.databinding.FragmentTestyBinding

/**
 * A simple [Fragment] subclass
 * for testing incase of bugs or whatever comes
 */
class TestyFragment : Fragment() {

    private lateinit var binding : FragmentTestyBinding

    companion object {
        private const val IMAGES_LIST_KEY = "images"
        fun newInstance(image: String?): TestyFragment =
            TestyFragment().also {
                it.arguments = Bundle().apply { putString(IMAGES_LIST_KEY, image) }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = arguments!!.getString(IMAGES_LIST_KEY)
        binding.imageView.load(
              image
        )
    }
}
