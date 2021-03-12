package com.example.warehouse.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class WarehouseMemStore : WarehouseStore, AnkoLogger {

    val whouses = ArrayList<WarehouseModel>()

    override fun findAll(): List<WarehouseModel> {
        return whouses
    }

    override fun create(warehouse: WarehouseModel) {
        warehouse.id = getId()
        whouses.add(warehouse)
        logAll()
    }

    override fun update(warehouse: WarehouseModel) {
        var foundWarehouse: WarehouseModel? = whouses.find { p -> p.id == warehouse.id }
        if (foundWarehouse != null) {
            foundWarehouse.stname= warehouse.stname
            foundWarehouse.stquantity = warehouse.stquantity
            foundWarehouse.stcountry = warehouse.stcountry
            foundWarehouse.palletnumber = warehouse.palletnumber
            foundWarehouse.pallettype = warehouse.pallettype
            logAll();
        }
    }

    override fun delete(warehouse: WarehouseModel) {
        whouses.remove(warehouse)
    }

    fun logAll(){
        whouses.forEach {info("${it}")}
    }

}