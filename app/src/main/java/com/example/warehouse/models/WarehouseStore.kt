package com.example.warehouse.models

interface WarehouseStore {
    fun findAll(): List<WarehouseModel>
    fun create(warehouse: WarehouseModel)
    fun update(warehouse: WarehouseModel)
    fun delete(warehouse: WarehouseModel)
}