package com.example.warehouse.main

import android.app.Application
import com.example.warehouse.models.WarehouseJSONStore
import com.example.warehouse.models.WarehouseStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


class MainApp : Application(), AnkoLogger {


    lateinit var whouses: WarehouseStore

    override fun onCreate() {
        super.onCreate()
        whouses = WarehouseJSONStore(applicationContext)
        info("Garage started")
    }

}