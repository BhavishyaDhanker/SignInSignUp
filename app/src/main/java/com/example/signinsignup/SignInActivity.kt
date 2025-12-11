package com.example.signinsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


lateinit var databaseReference: DatabaseReference


class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val btnsignin = findViewById<CardView>(R.id.btnsignIn)
        val signup = findViewById<TextView>(R.id.signUp)
        val TIusername = findViewById<TextInputEditText>(R.id.username)
        val TIpassword = findViewById<TextInputEditText>(R.id.pass)


        btnsignin.setOnClickListener {

            // Toast.makeText(this, "Sign In Button Clicked!", Toast.LENGTH_SHORT).show()

            val username = TIusername.text.toString()
            val password = TIpassword.text.toString()
            if( username.isNotEmpty()){

                readData(username)
            }else{
                Toast.makeText(this, "Please enter username !!" , Toast.LENGTH_SHORT).show()
            }

        }


        signup.setOnClickListener {

            intent = Intent(this , SignUpActivity ::class.java)
            startActivity(intent)
        }
    }

    companion object
    {
        const val Key1 ="com.example.signinsignup.SignInActivity.adm"
        const val Key2 ="com.example.signinsignup.SignInActivity.name"
        const val Key3 ="com.example.signinsignup.SignInActivity.usrnme"

    }
    fun readData(username : String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(username).get().addOnSuccessListener {

            if(it.exists()){

                val adm = it.child("adm").value.toString()
                val name = it.child("name").value.toString()

                val intentsignin = Intent(this , WelcomeActivity::class.java)
                intentsignin.putExtra(Key1, adm.toString())
                intentsignin.putExtra(Key2 , name.toString())
                intentsignin.putExtra(Key3, username)

                startActivity(intentsignin)


            }else{

                Toast.makeText(this, "User doesn't exist " , Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {


            Toast.makeText(this, "Something went wrong." , Toast.LENGTH_SHORT).show()
        }
    }


}