package com.tailorfit.android.tailorfitapp.gig


import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.tailorfit.android.Constants.Misc
import com.tailorfit.android.R
import com.tailorfit.android.base.BaseViewModel
import com.tailorfit.android.base.BaseViewModelFragment
import com.tailorfit.android.databinding.FragmentAddGigDetailsBinding
import com.tailorfit.android.extensions.createImageFile
import com.tailorfit.android.extensions.hasPermissions
import java.io.File
import java.lang.Exception
import javax.inject.Inject


class AddGigDetailsFragment : BaseViewModelFragment() {

    private lateinit var binding : FragmentAddGigDetailsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var photoFile: File

    private val cameraAndStoragePermissions =
        arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)

    companion object {
        private const val CAMERA_AND_STORAGE_PERMISSION_REQUEST_CODE = 100
        private const val REQUEST_IMAGE_CAPTURE_CODE = 101
    }

    private lateinit var viewModel :  GigViewModel
    private val addGigImageDetailsAdapter : AddGigImageDetailsAdapter by lazy {
        AddGigImageDetailsAdapter(AddGigImageDetailsAdapter.OnclickListener{
          if (checkPermissions()) startImageCaptureProcess()
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
        if (takePictureIntent.resolveActivity(mainActivity.packageManager) != null){
             photoFile = try{
                 mainActivity.createImageFile()
             } catch (e : Exception){
                 showSnackBar(getString(R.string.something_went_wrong))
                 return
             }
            val photoUri = FileProvider.getUriForFile(mainActivity, Misc.FILE_PROVIDER_AUTHORITY, photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_CODE)
        }
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
        ) startImageCaptureProcess()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE_CODE && resultCode == Activity.RESULT_OK) {
//            binding.avatarImageView.load(photoFile) {
//                crossfade(true)
//                transformations(CircleCropTransformation())
//            }
            viewModel.uploadGigStyle("", SizedFileRequestBody(photoFile))
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
