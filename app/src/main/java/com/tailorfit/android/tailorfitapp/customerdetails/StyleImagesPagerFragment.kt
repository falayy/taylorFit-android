/**
 * Copyright (c) 2020 Falaye Iyanuoluwa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tailorfit.android.tailorfitapp.customerdetails

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.palette.graphics.Palette
import coil.Coil
import coil.api.get
import coil.api.load
import coil.bitmappool.BitmapPool
import coil.request.CachePolicy
import coil.request.LoadRequest
import coil.request.LoadRequestBuilder
import coil.size.Size
import coil.transform.Transformation
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseFragment
import com.tailorfit.android.databinding.FragmentStyleImagesPagerBinding
import kotlinx.android.synthetic.main.toolbar_layout.*
import java.io.IOException
import java.net.URL
import javax.inject.Inject

class StyleImagesPagerFragment : BaseFragment() {

    private lateinit var binding: FragmentStyleImagesPagerBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var customerDetailsViewModel: CustomerDetailsViewModel

    private var imageBitMap: Bitmap? = null

    companion object {
        private const val IMAGE_LIST_KEY = "images"
        fun newInstance(image: String?): StyleImagesPagerFragment =
            StyleImagesPagerFragment().also {
                it.arguments = Bundle().apply { putString(IMAGE_LIST_KEY, image) }
            }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStyleImagesPagerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        val image = arguments!!.getString(IMAGE_LIST_KEY)
        customerDetailsViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CustomerDetailsViewModel::class.java)
        binding.styleImages.load(image) {
            crossfade(true)
        }
//        customerDetailsViewModel.createPalette(image!!)
    }
}
