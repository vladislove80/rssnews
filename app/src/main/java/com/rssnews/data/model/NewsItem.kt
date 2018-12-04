package com.rssnews.data.model

/**
 * Created by Vladyslav Ulianytskyi on 29.11.2018.
 */
data class NewsItem(
    val title: String = "",
    val date: String = "",
    val author: String = "",
    val newsLink: String = "",
    val imageLink: String = "",
    val imageDescription: String = "",
    val description: String = ""
)