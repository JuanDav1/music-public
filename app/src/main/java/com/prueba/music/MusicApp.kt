package com.prueba.music

import android.app.Application
import com.prueba.music.injection.AppComponent
import com.prueba.music.injection.AppModule
import com.prueba.music.injection.DaggerAppComponent
import com.prueba.music.injection.UtilsModule

class MusicApp : Application()  {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        context = this
       appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).utilsModule(UtilsModule()).build()
    }

    fun getAppComponent() : AppComponent {
        return appComponent
    }

    companion object {
        var context: MusicApp? = null
            private set
    }
}