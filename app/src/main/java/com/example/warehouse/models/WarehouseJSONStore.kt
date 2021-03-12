package com.example.warehouse.models

import android.content.Context
import com.example.warehouse.helpers.exists
import com.example.warehouse.helpers.read
import com.example.warehouse.helpers.write
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import java.util.*

val JSON_FILE = "stock.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<WarehouseModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class WarehouseJSONStore : WarehouseStore, AnkoLogger {

    val context: Context
    var whouses = mutableListOf<WarehouseModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<WarehouseModel> {
        return whouses
    }

    override fun create(warehouse: WarehouseModel) {
        warehouse.id = generateRandomId()
        whouses.add(warehouse)
        serialize()
    }

    override fun delete(warehouse: WarehouseModel) {
        whouses.remove(warehouse)
        serialize()
    }

    override fun update(warehouse: WarehouseModel) {
        val garagesList = findAll() as ArrayList<WarehouseModel>
        var foundWarehouse: WarehouseModel? = garagesList.find { p ->
            p.id == warehouse.id
        }
            if (foundWarehouse != null) {
                foundWarehouse.stname= warehouse.stname
                foundWarehouse.stquantity = warehouse.stquantity
                foundWarehouse.stcountry = warehouse.stcountry
                foundWarehouse.palletnumber = warehouse.palletnumber
                foundWarehouse.pallettype = warehouse.pallettype
            serialize();
        }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(whouses, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        whouses = Gson().fromJson(jsonString, listType)
    }
}