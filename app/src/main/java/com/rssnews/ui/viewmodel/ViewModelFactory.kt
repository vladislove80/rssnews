package com.rssnews.ui.viewmodel

import android.annotation.SuppressLint
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rssnews.data.NewsRepository
import com.rssnews.data.model.NewsItem
import com.rssnews.data.source.DataSourceImpl
import com.rssnews.data.source.DataSource
import com.rssnews.data.source.RemoteDataSourceImpl

/**
 * Created by Vladyslav Ulianytskyi on 05.12.2018.
 */
class ViewModelFactory private constructor(private val newsRepository: DataSource<List<NewsItem>>) :
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
                        NewsRepository.getInstance(RemoteDataSourceImpl, DataSourceImpl)
                    ).also { INSTANCE = it }
            }


        @VisibleForTesting
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}