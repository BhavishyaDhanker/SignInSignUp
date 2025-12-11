package com.example.signinsignup

import android.content.Intent
import android.widget.Toast
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signupbtn = findViewById<Button>(R.id.btnsignup)
        val Etname = findViewById<TextInputEditText>(R.id.ETname)
        val Etusername = findViewById<TextInputEditText>(R.id.ETusername)
        val Etadm = findViewById<TextInputEditText>(R.id.ETadmNo)
        val Etpass = findViewById<TextInputEditText>(R.id.ETpassword)
        val signin = findViewById<TextView>(R.id.signIn)

        signupbtn.setOnClickListener {

            val name = Etname.text.toString()
            val adm = Etadm.text.toString()
            val username = Etusername.text.toString()
            val pass = Etpass.text.toString()

            val user = User(name, adm, username, pass)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(username).setValue(user).addOnSuccessListener {
                Toast.makeText(this , "User Registered", Toast.LENGTH_SHORT).show()
                Etpass.text?.clear()
                Etname.text?.clear()
                Etusername.text?.clear()
                Etadm.text?.clear()
            }.addOnFailureListener {
                Toast.makeText(this, "Error user not registered", Toast.LENGTH_SHORT).show()
            }

        }

        signin.setOnClickListener {


            intent = Intent(this , SignInActivity::class.java)
            startActivity(intent)
        }

    }
}