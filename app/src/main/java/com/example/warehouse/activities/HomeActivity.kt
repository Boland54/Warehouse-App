package com.example.warehouse.activities

import android.content.Intent
import android.location.Location
import android.os.Bundle

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.warehouse.R
import com.example.warehouse.models.WarehouseModel
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult

class HomeActivity : AppCompatActivity() {

    var warehouse1 = WarehouseModel()
    val LOCATION_REQUEST = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        stockLocation.setOnClickListener {
val intent = Intent(this, WarehouseListActivity::class.java)
            startActivity(intent)
        }


        checkLocation.setOnClickListener() {
            val intent = Intent(this, TodoActivity::class.java)
            startActivity(intent)
        }

        mapLocation.setOnClickListener {
            val location = com.example.warehouse.models.Location(52.2659653, -8.2699628, 15f)
            if (location.zoom != 0f) {
                location.lat =  location.lat
                location.lng = location.lng
                location.zoom = location.zoom
            }
            startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
        }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        }
    }
