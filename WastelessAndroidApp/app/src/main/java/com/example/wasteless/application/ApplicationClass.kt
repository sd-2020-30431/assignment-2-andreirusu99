package com.example.wasteless.application

import android.app.Application
import com.example.wasteless.application.AppModules
import com.orhanobut.hawk.Hawk
import org.koin.core.context.startKoin

@Suppress("unused")
class ApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        Hawk.init(applicationContext).build()
        startKoin {
            modules(AppModules.modules)
        }
    }
}
