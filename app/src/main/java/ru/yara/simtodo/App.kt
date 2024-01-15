package ru.yara.simtodo

import android.app.Application
import ru.yara.simtodo.di.AppComponent
import ru.yara.simtodo.di.DaggerAppComponent

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        // create component
        dagger = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}