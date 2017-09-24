package com.github.hachimori.kotlingalleryapplication.model.network

import com.github.hachimori.kotlingalleryapplication.BuildConfig
import com.github.hachimori.kotlingalleryapplication.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Creates Retrofit instance for accessing 500px Web API.
 *
 * Created by benhachimori on 2017/02/04.
 */
class ServiceGenerator {
    
    companion object {
        val INSTANCE: GalleryService 
            get() {
                // Set "consumer_key" parameter for every request 
                val httpClientBuilder = OkHttpClient.Builder()
                        .addInterceptor { chain ->

                            val httpUrl = chain.request()
                                    .url()
                                    .newBuilder()
                                    .addQueryParameter("consumer_key", Constants.CONSUMER_KEY)
                                    .build()

                            val request = chain.request().newBuilder()
                                    .url(httpUrl)
                                    .build()

                            chain.proceed(request)
                        }

                // Display the result of Web API request on Logcat
                if (BuildConfig.DEBUG) {
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.level = HttpLoggingInterceptor.Level.BASIC
                    httpClientBuilder.addInterceptor(interceptor)
                }

                val retrofit = Retrofit.Builder()
                        .baseUrl(Constants.API_BASE_URL)
                        .client(httpClientBuilder.build())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()

                return retrofit.create(GalleryService::class.java)
            }
    }
}
