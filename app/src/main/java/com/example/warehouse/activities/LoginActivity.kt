package com.example.warehouse.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.warehouse.R
import kotlinx.android.synthetic.main.activity_warehouse.*
import org.jetbrains.anko.startActivityForResult


class LoginActivity : AppCompatActivity() {

    private var Name: EditText? = null
    private var Password: EditText? = null
    private var Info: TextView? = null
    private var Login: Button? = null
    private var counter = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.warehouse.R.layout.activity_login)


        Name = findViewById(com.example.warehouse.R.id.etName) as EditText
        Password = findViewById(com.example.warehouse.R.id.etPassword) as EditText
        Info = findViewById(com.example.warehouse.R.id.tvInfo) as TextView
        Login = findViewById(com.example.warehouse.R.id.btnLogin) as Button

        Info!!.text = "No of attempts remaining: 5"

        Login!!.setOnClickListener {
                validate(Name!!.text.toString(), Password!!.text.toString())
            }
        }


    private fun validate(userName: String, userPassword: String) {
        if (userName == "Adam" && userPassword == "1234") {
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(intent)
        } else {
            counter--
            Info!!.text = "No of attempts remaining: $counter"
            if (counter == 0) {
                Login!!.setEnabled(false)
            }
        }
    }

}