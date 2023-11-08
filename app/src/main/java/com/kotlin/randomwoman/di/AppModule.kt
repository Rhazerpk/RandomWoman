package com.kotlin.randomwoman.di

import com.kotlin.randomwoman.data.remote.WomanApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Singleton
    @Provides
    fun providesRandomWomanApi(moshi: Moshi): WomanApi {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(WomanApi::class.java)
    }
}