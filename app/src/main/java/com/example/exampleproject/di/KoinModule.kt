package com.example.exampleproject.di

import com.example.exampleproject.AppConfig
import com.example.exampleproject.network.NewsApi
import com.example.exampleproject.repository.NewsRepository
import com.example.exampleproject.ui.NewsViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun injectFeature() = loadFeature

private val loadFeature by lazy {
    GlobalContext.loadKoinModules(
        listOf(
            networkModule,
            viewModelModule,
            repositoryModule
        )
    )
}

val networkModule = module {
    single { provideRetrofit(get()) }
    single { provideOkHttpClient() }
    single { provideNewsApi(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(AppConfig.BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .readTimeout(40, TimeUnit.SECONDS)
        .connectTimeout(40, TimeUnit.SECONDS)
        .build()
}

fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

val viewModelModule = module {
    viewModel { NewsViewModel(get()) }
}

val repositoryModule = module {
    single { NewsRepository(get()) }
}