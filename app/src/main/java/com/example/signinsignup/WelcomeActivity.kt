package com.example.signinsignup

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val TVwelcome = findViewById<TextView>(R.id.TVwelcome)
        val TVadm = findViewById<TextView>(R.id.TVadm)
        val TVusrnme = findViewById<TextView>(R.id.TVusrnm)


        val name = intent.getStringExtra(SignInActivity.Key2)
        val admNo = intent.getStringExtra(SignInActivity.Key1)
        val username = intent.getStringExtra(SignInActivity.Key3)

        TVwelcome.text = "Welcome $name"
        TVadm.text = "Admission Number : $admNo"
        TVusrnme.text = "Username : $username"



    }
}