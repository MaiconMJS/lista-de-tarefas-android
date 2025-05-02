package com.newoverride.listadetarefas

import android.app.Application
import com.newoverride.listadetarefas.infra.database.AppDatabase

class App : Application() {
    lateinit var db: AppDatabase
    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDatabase(this)
    }
}