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
package com.tailorfit.android.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapFactory.Options
import timber.log.Timber
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun File.resize(height: Int, width: Int) {
    try {
        val bitmap: Bitmap = getScaledBitmap(height, width)
        val fileOutputStream = FileOutputStream(this)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 96, fileOutputStream)
        fileOutputStream.close()
    } catch (e: IOException) {
        Timber.e(e)
    }
}

fun File.getScaledBitmap(
    height: Int,
    width: Int
): Bitmap { // Get the dimensions of the bitmap
    val bmOptions = Options()
    bmOptions.inJustDecodeBounds = true
    BitmapFactory.decodeFile(absolutePath, bmOptions)
    // Determine how much to scale down the image
    val scaleFactor: Int = bmOptions.calculateInSampleSize(width, height)
    // Decode the image file into a Bitmap sized to fill the View
    bmOptions.inJustDecodeBounds = false
    bmOptions.inSampleSize = scaleFactor
    bmOptions.inPurgeable = true
    return BitmapFactory.decodeFile(absolutePath, bmOptions)
}

fun Options.calculateInSampleSize(reqWidth: Int, reqHeight: Int): Int {
    val height = outHeight
    val width = outWidth
    var inSampleSize = 1
    if (height > reqHeight || width > reqWidth) {
        val halfHeight = height / 2
        val halfWidth = width / 2
        while (halfHeight / inSampleSize >= reqHeight &&
            halfWidth / inSampleSize >= reqWidth
        ) {
            inSampleSize *= 2
        }
    }
    return inSampleSize
}