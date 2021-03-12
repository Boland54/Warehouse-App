package com.example.warehouse.activities


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.warehouse.R
import com.example.warehouse.models.WarehouseModel
import kotlinx.android.synthetic.main.card_warehouse.view.*

interface WarehouseListener {
    fun onWarehouselick(warehouse: WarehouseModel)
}

class WarehouseAdapter constructor(private var warehouses: List<WarehouseModel>,
                                private val listener: WarehouseListener) : RecyclerView.Adapter<WarehouseAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_warehouse, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val garage = warehouses[holder.adapterPosition]
        holder.bind(garage, listener)
    }

    override fun getItemCount(): Int = warehouses.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(warehouse: WarehouseModel, listener : WarehouseListener) {
            itemView.whouseMake.text = warehouse.stname
            itemView.model.text = warehouse.stquantity
            itemView.year.text = warehouse.stcountry
            itemView.litre.text = warehouse.pallettype
            itemView.doors.text = warehouse.palletnumber
            itemView.setOnClickListener { listener.onWarehouselick(warehouse) }
        }
    }
}