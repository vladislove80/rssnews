package com.rssnews.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */
@Parcelize
data class Categories(val categories: MutableList<Category>) : Parcelable