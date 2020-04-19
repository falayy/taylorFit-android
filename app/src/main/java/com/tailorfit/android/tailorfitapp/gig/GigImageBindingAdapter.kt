package com.tailorfit.android.tailorfitapp.gig

import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import coil.api.load
import coil.transform.CircleCropTransformation
import com.tailorfit.android.R
import com.tailorfit.android.extensions.hide
import com.tailorfit.android.extensions.show

@BindingAdapter("bindImageProgressBar")
fun bindImageProgess(
    progressBar: ProgressBar,
    uploadStatus: ImageUploadStatus
) {
    when (uploadStatus) {
        ImageUploadStatus.NOT_UPLOADED -> {
            progressBar.hide()
        }
        ImageUploadStatus.UPLOADING -> {
            progressBar.show()
        }
        ImageUploadStatus.SUCCESS -> {
            progressBar.hide()
        }
        ImageUploadStatus.FAILED -> {
            progressBar.hide()
        }
    }
}

@BindingAdapter("bindImage")
fun bindImage(
    imageView: ImageView,
    uploadStatus: ImageUploadStatus
) {
    when (uploadStatus) {
        ImageUploadStatus.NOT_UPLOADED -> {
            imageView.hide()
        }
        ImageUploadStatus.UPLOADING -> {
            imageView.hide()
        }
        ImageUploadStatus.SUCCESS -> {
            imageView.load(R.drawable.image_upload_done_status) {
                transformations(CircleCropTransformation())
            }
            imageView.show()
        }
        ImageUploadStatus.FAILED -> {
            imageView.load(R.drawable.image_upload_refresh) {
                transformations(CircleCropTransformation())
            }
            imageView.show()
        }
    }
}