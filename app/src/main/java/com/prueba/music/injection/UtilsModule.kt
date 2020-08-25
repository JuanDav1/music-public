package com.prueba.music.injection

import androidx.lifecycle.ViewModelProvider
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.prueba.music.api.AplicationApi
import com.prueba.music.repositories.LocalRepository
import com.prueba.music.repositories.RemoteRepository
import com.prueba.music.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class UtilsModule {


    private val baseUrl = "http://ws.audioscrobbler.com/2.0/"

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return builder.setLenient().create()
    }


    @Provides
    @Singleton
    fun  provideOkHttpClient(): OkHttpClient {

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .build()
            chain.proceed(request)
        }
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS)

        return httpClient.build()

    }

    @Provides
    @Singleton
    fun getApiCall(retrofit: Retrofit): AplicationApi {
        return retrofit.create(AplicationApi::class.java)
    }


    @Provides
    @Singleton
    fun getViewModelFactory(remoteRepository: RemoteRepository, localReposiroty: LocalRepository): ViewModelProvider.Factory {
        return ViewModelFactory(remoteRepository, localReposiroty)
    }

    @Provides
    @Singleton
    fun getRemoteRepository(aplicationApiCall: AplicationApi): RemoteRepository {
        return RemoteRepository(aplicationApiCall)
    }

    @Provides
    @Singleton
    fun getLocalRepository(): LocalRepository {
        return LocalRepository()
    }


}