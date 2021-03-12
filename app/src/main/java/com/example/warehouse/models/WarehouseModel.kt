package com.example.warehouse.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WarehouseModel(var id: Long = 0,
                          var stname: String = "",
                          var stquantity: String = "",
                          var stcountry:String= "",
                          var pallettype: String ="",
                          var palletnumber:String = "") : Parcelable

@Parcelize
data class Location(var lat: Double = 0.0,
                    var lng: Double = 0.0,
                    var zoom: Float = 0f) : Parcelable
