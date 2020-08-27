package com.prueba.music.injection

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.prueba.music.api.AplicationApi
import com.prueba.music.database.AplicationDB
import com.prueba.music.database.DataBaseDao
import com.prueba.music.repositories.LocalRepository
import com.prueba.music.repositories.RemoteRepository
import com.prueba.music.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class UtilsModule {
    @Provides
    @Singleton
    fun getInstanceDB(context: Context): AplicationDB {
        synchronized(this) {
            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AplicationDB::class.java,
                    "music_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }

    private val baseUrl = "https://ws.audioscrobbler.com/2.0/"

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(baseUrl)
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

    @Volatile
    private var INSTANCE: AplicationDB? = null



    @Singleton
    @Provides
    fun provideDao(aplicationDB: AplicationDB): DataBaseDao {
        return aplicationDB.dataBaseDao()
    }
    @Provides
    @Singleton
    fun getLocalRepository(dataBaseDao: DataBaseDao): LocalRepository {
        return LocalRepository (dataBaseDao)
    }



}