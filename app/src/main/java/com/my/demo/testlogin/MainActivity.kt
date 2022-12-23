package com.my.demo.testlogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener { redirectToHome() }
    }

    fun redirectToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("var1", "hola")
        startActivity(intent)
    }

}