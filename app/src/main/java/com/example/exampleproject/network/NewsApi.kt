package com.example.exampleproject.network

import com.example.exampleproject.AppConfig
import com.example.exampleproject.model.News
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET


interface NewsApi {

    @GET("news?access_key=${AppConfig.API_KEY}")
    fun getNews(): Observable<News>

}