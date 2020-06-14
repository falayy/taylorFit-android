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
package com.tailorfit.android.tailorfitapp.gig

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import coil.transform.CircleCropTransformation
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.tailorfit.android.Constants
import com.tailorfit.android.Constants.Misc
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentAddGigDetailsBinding
import com.tailorfit.android.extensions.*
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.CreateGigRequest
import java.io.File
import java.lang.Exception
import javax.inject.Inject

enum class UPLOADTYPE { GALLERY, CAMERA }
class AddGigDetailsFragment : BaseViewModelFragment(), AddGigImageDetailsAdapter.OnclickListener {

    private lateinit var binding: FragmentAddGigDetailsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var photoFile: File
    private lateinit var photoUri: Uri

    private lateinit var gigAdapterViewHolder: AddGigImageDetailsAdapter.GigImageViewHolder

    private val cameraAndStoragePermissions =
        arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

    companion object {
        private const val CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE = 100
        private const val REQUEST_IMAGE_CAPTURE_CODE = 101
        private const val PICK_IMAGE_REQUEST = 102
    }

    private lateinit var viewModel: GigViewModel
    private val addGigImageDetailsAdapter: AddGigImageDetailsAdapter by lazy {
        AddGigImageDetailsAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddGigDetailsBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        daggerAppComponent.inject(this)
        setUpToolbar()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GigViewModel::class.java)
        viewModel.getImagePlaceHolders()
        getImagePlaceHolder()

        binding.createGigButton.setOnClickListener {
            createGig()
        }
    }

    private fun createGig() {
        val args = AddGigDetailsFragmentArgs.fromBundle(arguments!!)
        val gigRequestArgs = args.createGig
        val createGigRequest = CreateGigRequest(
            prefsValueHelper.getCustomerId(),
            "12 December, 2019",
            binding.additionalEditText.stringContent(),
            gigRequestArgs.price,
            null,
            gigRequestArgs.styleName,
            gigRequestArgs.title,
            prefsValueHelper.getUserId()
        )

        viewModel.createGig(
            prefsValueHelper.getAccessToken()!!,
            createGigRequest
        )

        viewModel.createGigResponse.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it.gender) {
                    Constants.Gender.FEMALE -> {
                        findNavController().navigate(AddGigDetailsFragmentDirections.actionAddGigDetailsToFemaleMeasurementFragment())
                    }
                    Constants.Gender.MALE -> {
                        findNavController().navigate(AddGigDetailsFragmentDirections.actionAddGigDetailsToMaleMeasurementFragment())
                    }
                }
            }
        })
    }

    private fun getImagePlaceHolder() {
        viewModel.imagePlaceHolder.observe(this, Observer {
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

    private fun startImageCaptureProcess() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(mainActivity.packageManager) != null) {
            photoFile = try {
                mainActivity.createImageFile()
            } catch (e: Exception) {
                showSnackBar(getString(R.string.something_went_wrong))
                return
            }
            photoUri =
                FileProvider.getUriForFile(mainActivity, Misc.FILE_PROVIDER_AUTHORITY, photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_CODE)
        }
    }

    private fun startImageSelectionProcess() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun checkPermissions(): Boolean {
        return if (mainActivity.hasPermissions(cameraAndStoragePermissions)) true
        else {
            requestPermissions(
                cameraAndStoragePermissions,
                CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE
            )
            false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE &&
            confirmPermissionResults(grantResults)
        ) startImageSelectionProcess()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE_CODE -> {
                    uploadImage(gigAdapterViewHolder, photoUri)
                }

                PICK_IMAGE_REQUEST -> {
                    uploadImageFromGallery(gigAdapterViewHolder, data?.data!!)
                }
            }
        }
    }

    private fun confirmPermissionResults(results: IntArray): Boolean {
        results.forEach {
            if (it != PackageManager.PERMISSION_GRANTED) return false
        }
        return true
    }

    private fun uploadImage(
        gigImageViewHolder: AddGigImageDetailsAdapter.GigImageViewHolder,
        uri: Uri?
    ) {
        prepareHolderForUpload(gigImageViewHolder)
        viewModel.uploadGigStyle(uri!!)
    }

    private fun uploadImageFromGallery(
        gigAdapterViewHolder: AddGigImageDetailsAdapter.GigImageViewHolder,
        data: Uri
    ) {
        prepareHolderForUpload(gigAdapterViewHolder, data)
        viewModel.uploadGigStyle(data)
    }

    private fun prepareHolderForUpload(
        gigImageViewHolder: AddGigImageDetailsAdapter.GigImageViewHolder
    ) {
        gigImageViewHolder.itemView.isClickable = false
        gigImageViewHolder.binding.imageStyle.load(photoFile) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        observeImageUpload(null, photoFile)
    }

    private fun prepareHolderForUpload(
        gigImageViewHolder: AddGigImageDetailsAdapter.GigImageViewHolder,
        uri: Uri
    ) {
        gigImageViewHolder.itemView.isClickable = false
        gigImageViewHolder.binding.imageStyle.load(uri) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        observeImageUpload(uri, null)
    }

    private fun setUpToolbar() = mainActivity.run {
        setUpToolBar("", false)
        invalidateToolbarElevation(0)
    }

    override fun onClickItem(
        gigImageViewHolder: AddGigImageDetailsAdapter.GigImageViewHolder,
        itemPosition: Int
    ) {
        gigAdapterViewHolder = gigImageViewHolder
        MaterialDialog(context!!).show {
            listItems(R.array.list_of_image_options) { _, index, _ ->
                when (index) {
                    0 -> {
                        if (checkPermissions()) startImageCaptureProcess()
                    }
                    1 -> {
                        if (checkPermissions()) startImageSelectionProcess()
                    }
                }
            }
        }
    }

    private fun observeImageUpload(uri: Uri?, file: File?) {
        viewModel.imageUploadStatus.observe(viewLifecycleOwner, Observer {
            when (it) {
                ImageUploadStatus.NOT_UPLOADED -> {
                    gigAdapterViewHolder.binding.apply {
                        indeterminateImageUploadProgressBar.hide()
                        imageUploadStatusView.hide()
                    }
                }
                ImageUploadStatus.UPLOADING -> {
                    gigAdapterViewHolder.binding.apply {
                        indeterminateImageUploadProgressBar.show()
                        imageUploadStatusView.hide()
                    }
                }
                ImageUploadStatus.SUCCESS -> {
                    gigAdapterViewHolder.binding.apply {
                        imageUploadStatusView.load(R.drawable.image_upload_done_status) {
                            transformations(CircleCropTransformation())
                        }
                        indeterminateImageUploadProgressBar.hide()
                        imageUploadStatusView.show()
                    }
                }
                ImageUploadStatus.FAILED -> {
                    gigAdapterViewHolder.binding.apply {
                        imageUploadStatusView.load(R.drawable.image_upload_refresh) {
                            transformations(CircleCropTransformation())
                        }
                        imageUploadStatusView.show()
                        indeterminateImageUploadProgressBar.hide()
                    }
                }
            }
        })
    }

    override fun getViewModel(): BaseViewModel = viewModel
}
