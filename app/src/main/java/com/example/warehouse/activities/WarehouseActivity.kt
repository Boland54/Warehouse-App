package com.example.warehouse.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.warehouse.R
import com.example.warehouse.main.MainApp
import com.example.warehouse.models.WarehouseModel
import kotlinx.android.synthetic.main.activity_warehouse.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast




class WarehouseActivity : AppCompatActivity(), AnkoLogger {

    var whouse = WarehouseModel()
    lateinit var app : MainApp
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warehouse)
        app = application as MainApp

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("cars_edit")) {
            edit = true
            whouse = intent.extras.getParcelable<WarehouseModel>("cars_edit")
            stockName.setText(whouse.stname)
            stockQuantity.setText(whouse.stquantity)
            country.setText(whouse.stcountry)
            palletType.setText(whouse.pallettype)
            palletNumber.setText(whouse.palletnumber)

            btnAdd.setText(R.string.save_stock)
        }



        btnAdd.setOnClickListener() {
            whouse.stname = stockName.text.toString()
            whouse.stquantity = stockQuantity.text.toString()
            whouse.stcountry = country.text.toString()
            whouse.pallettype = palletType.text.toString()
            whouse.palletnumber = palletNumber.text.toString()

            if (whouse.stname.isEmpty() or whouse.stquantity.isEmpty() or whouse.stcountry.isEmpty() or whouse.pallettype.isEmpty() or
            whouse.palletnumber.isEmpty()) {
                toast(R.string.enter_stock_title)
            } else {
                if (edit) {
                    app.whouses.update(whouse.copy())
                } else {
                    app.whouses.create(whouse.copy())
                }
            }
            info("add Button Pressed: $stockName")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_warehouse, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.whouses.delete(whouse)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        }
    }
