package com.prueba.music.injection

import com.prueba.music.views.activity.ArtistsActivity
import com.prueba.music.views.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, UtilsModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(artistsActivity: ArtistsActivity)

}