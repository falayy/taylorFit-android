package com.tailorfit.android.tailorfitapp.models.local

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.android.parcel.Parcelize


@Parcelize
data class PageDataModel (@StringRes val title: Int, @StringRes val description: Int, @DrawableRes val illustrationGraphic: Int) :
    Parcelable