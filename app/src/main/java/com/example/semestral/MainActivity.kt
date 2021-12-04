package com.example.semestral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.PasswordInput)
        loginBtn = findViewById(R.id.loginBtn)

        loginBtn.setOnClickListener {
            if(emailInput.getText().toString() == "pepe@correo.com" && passwordInput.getText().toString() == "pacopaco") {
                val intent = Intent(this,  HomeActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Bienvenido, usuario", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Datos erroneos", Toast.LENGTH_LONG).show()
            }
        }

    }
}