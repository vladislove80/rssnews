package com.rssnews.util

import android.view.View
import java.util.regex.Pattern


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

fun getImageSrcFromHTML(html: String): String {
    Pattern.compile("<img[^>]*src=[\\\"']([^\\\"^']*)").matcher(html).apply {
        return if (this.find()) {
            val src = this.group()
            src.substring(src.indexOf("src=") + 5, src.length)
        } else ""
    }
}

fun getImageDescription(htmlDescription: String): String {
    Pattern.compile("<img[^>]*title=[\\\"']([^\\\"^']*)").matcher(htmlDescription).apply {
        return if (this.find()) {
            val src = this.group()
            src.substring(src.indexOf("title=") + 7, src.length)
                    .replace("&#39;", "\'")
        } else ""
    }
}

fun getNewsDescription(htmlDesc: String): String {
    return htmlDesc.substring(htmlDesc.indexOf("<p>") + 3, htmlDesc.indexOf("</p>"))
}
