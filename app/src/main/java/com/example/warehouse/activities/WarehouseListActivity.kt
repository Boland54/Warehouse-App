package com.example.warehouse.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.warehouse.R
import com.example.warehouse.main.MainApp
import com.example.warehouse.models.WarehouseModel
import kotlinx.android.synthetic.main.activity_warehouse_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult


class WarehouseListActivity : AppCompatActivity(), WarehouseListener {

    lateinit var app: MainApp


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warehouse_list)
        app = application as MainApp


        //layout and populate for display
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        loadStock()

        //enable action bar and set title
        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
                when (item?.itemId) {
                    R.id.item_home ->
                        startActivityForResult<HomeActivity>(0)
                    R.id.item_add ->
                        startActivityForResult<WarehouseActivity>(0)
                }

                return super.onOptionsItemSelected(item)
        }


    override fun onWarehouselick(warehouse: WarehouseModel) {
        startActivityForResult(intentFor<WarehouseActivity>().putExtra("cars_edit", warehouse), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadStock()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadStock() {
        showStock(app.whouses.findAll())
    }

    fun showStock (warehouses: List<WarehouseModel>) {
        recyclerView.adapter = WarehouseAdapter(warehouses, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}

