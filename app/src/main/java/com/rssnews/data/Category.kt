package com.rssnews.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */
@Parcelize
data class Category(val categoryName: String, val link: String, var isSelected: Boolean = false) : Parcelable