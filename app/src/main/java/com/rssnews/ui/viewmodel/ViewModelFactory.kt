package com.rssnews.ui.viewmodel

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rssnews.data.source.RemoteDataSource
import com.rssnews.data.NewsRepository
import com.rssnews.data.model.NewsItem
import com.rssnews.data.source.NewsDataSource
import com.rssnews.data.source.LocalDataSource

/**
 * Created by Vladyslav Ulianytskyi on 05.12.2018.
 */
class ViewModelFactory private constructor(private val newsRepository: NewsDataSource<List<NewsItem>>) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = (
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) NewsViewModel(
                newsRepository
            )
            else throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")) as T

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance() =
            INSTANCE
                ?: synchronized(ViewModelFactory::class.java) {
                INSTANCE
                    ?: ViewModelFactory(
                        NewsRepository.getInstance(RemoteDataSource, LocalDataSource)
                    ).also { INSTANCE = it }
            }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}