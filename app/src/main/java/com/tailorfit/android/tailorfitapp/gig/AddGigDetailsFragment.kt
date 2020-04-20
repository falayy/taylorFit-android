package com.tailorfit.android.tailorfitapp.gig


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import coil.api.load
import coil.transform.CircleCropTransformation
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItems
import com.tailorfit.android.Constants.Misc
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentAddGigDetailsBinding
import com.tailorfit.android.extensions.createImageFile
import com.tailorfit.android.extensions.hasPermissions
import com.tailorfit.android.extensions.stringContent
import com.tailorfit.android.tailorfitapp.PrefsValueHelper
import com.tailorfit.android.tailorfitapp.models.request.CreateGigRequest
import java.io.File
import java.lang.Exception
import javax.inject.Inject


class AddGigDetailsFragment : BaseViewModelFragment() {

    private lateinit var binding : FragmentAddGigDetailsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefsValueHelper: PrefsValueHelper

    private lateinit var photoFile: File
    private lateinit var  photoUri : Uri

    private var position = 0
    private lateinit var individualImageViewFromAdapter : ImageView

    private val cameraAndStoragePermissions =
        arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)

    companion object {
        private const val CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE = 100
        private const val REQUEST_IMAGE_CAPTURE_CODE = 101
        private const val PICK_IMAGE_REQUEST = 102
    }

    private  var listOptionsForImageCapture = listOf("Camera", "Gallery")
    private lateinit var viewModel :  GigViewModel
    private val addGigImageDetailsAdapter : AddGigImageDetailsAdapter by lazy {
        AddGigImageDetailsAdapter(AddGigImageDetailsAdapter.OnclickListener{
           MaterialDialog(mainActivity.applicationContext).show {
               // TODO if you can do this from the main activity like others
               listItems(R.string.camera) { _, _, _ ->
                   if (checkPermissions()) startImageCaptureProcess(it)
               }
               listItems(R.string.gallery) { _, _, _ ->
                   if (checkPermissions()) startImageSelectionProcess()

               }
           }
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
        binding.createGigButton.setOnClickListener {
            viewModel.createGig(
                prefsValueHelper.getAccessToken(),
                CreateGigRequest(
                    prefsValueHelper.getCustomerId(),
                    prefsValueHelper.getGigDueDate(),
                    binding.additionalEditText.stringContent(),
                    prefsValueHelper.getGigPrice(),
                    null,
                    prefsValueHelper.getGigStyleName(),
                    prefsValueHelper.getGigTitle(),
                    prefsValueHelper.getUserId()
                    )
                )

            viewModel.createGigResponse.observe(viewLifecycleOwner, Observer {
                if (it != null) {
                    when(it.gender) {
                        "male" -> {

                        }
                        "female" -> {

                        }
                    }
                }
            })
        }
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

    private fun startImageCaptureProcess(position : Int) {
        this.position = position
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(mainActivity.packageManager) != null){
             photoFile = try{
                 mainActivity.createImageFile()
             } catch (e : Exception){
                 showSnackBar(getString(R.string.something_went_wrong))
                 return
             }
            photoUri = FileProvider.getUriForFile(mainActivity, Misc.FILE_PROVIDER_AUTHORITY, photoFile)
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

    private fun checkPermissions() : Boolean {
        return if (mainActivity.hasPermissions(cameraAndStoragePermissions)) true
        else{
            requestPermissions(cameraAndStoragePermissions, CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE)
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
        ) startImageCaptureProcess(requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            REQUEST_IMAGE_CAPTURE_CODE -> {
                showImageOnImageView()
            }
            PICK_IMAGE_REQUEST ->{
                showImageOnImageView()
            }
        }
        viewModel.uploadGigStyle(photoUri)
    }

    private fun showImageOnImageView() {
        individualImageViewFromAdapter.load(photoFile) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }

    private fun confirmPermissionResults(results: IntArray): Boolean {
        results.forEach {
            if (it != PackageManager.PERMISSION_GRANTED) return false
        }
        return true
    }

    override fun getViewModel(): BaseViewModel = viewModel


}
