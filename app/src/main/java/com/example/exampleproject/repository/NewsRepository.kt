package com.example.exampleproject.repository

import com.example.exampleproject.model.News
import com.example.exampleproject.network.NewsApi
import io.reactivex.rxjava3.core.Observable

class NewsRepository (
    private val newsApi: NewsApi
) {

    fun getNews(): Observable<News> = newsApi.getNews()

}