package com.puldroid.imdb.rest

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val queryInterceptor = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", "a908fc789577ee9ead5deab36bcfbc1b")
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    private var gson: Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    private val retrofit = Retrofit.Builder()
        .client(queryInterceptor)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val service = retrofit.create(MovieService::class.java)


}