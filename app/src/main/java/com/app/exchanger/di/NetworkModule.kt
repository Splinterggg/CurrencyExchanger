package com.app.exchanger.di

import com.app.exchanger.data.source.CurrencyExchangeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): CurrencyExchangeApi {
        return Retrofit.Builder()
            .baseUrl(CurrencyExchangeApi.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(),
            )
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY),
                    )
                    .build(),
            )
            .build()
            .create(CurrencyExchangeApi::class.java)
    }
}
