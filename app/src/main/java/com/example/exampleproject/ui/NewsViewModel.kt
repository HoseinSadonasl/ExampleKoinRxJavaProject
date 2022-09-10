package com.example.exampleproject.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exampleproject.model.Data
import com.example.exampleproject.model.News
import com.example.exampleproject.repository.NewsRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {


    private var _newsList = MutableLiveData<List<Data>>()
    val newsList: MutableLiveData<List<Data>> = _newsList

    init {
        getNews()
    }

    private fun getNews() {

        val response = newsRepository.getNews()
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(newsListObserver())

    }

    private fun newsListObserver(): Observer<News> {
        return object : Observer<News> {
            override fun onSubscribe(d: Disposable) {
                // do something before
            }

            override fun onNext(news: News) {
                _newsList.postValue(news.data)
            }

            override fun onError(e: Throwable) {
                Log.e("onError", "onError: ${e.message}")
            }

            override fun onComplete() {
               // do something after complete
            }
        }
    }

}