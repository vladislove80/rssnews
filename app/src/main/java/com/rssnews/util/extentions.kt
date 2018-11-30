package com.rssnews.util

import android.view.View

/**
 * Created by Vladyslav Ulianytskyi on 28.11.2018.
 */

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
