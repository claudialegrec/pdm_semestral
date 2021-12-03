package com.example.semestral

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class UserProfileActivity : AppCompatActivity() {
    private lateinit var etNombre: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etTelefono: EditText
    private lateinit var etEmail: EditText
    private lateinit var userFoto: ImageView
    private lateinit var backBtn: Button

    private var foto: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        etNombre = findViewById(R.id.etNombre)
        etApellidos = findViewById(R.id.etApellidos)
        etTelefono = findViewById(R.id.etTelefono)
        etEmail = findViewById(R.id.etEmail)
        userFoto = findViewById(R.id.userFoto)
        backBtn = findViewById(R.id.backBtn)

//        backBtn.setOnClickListener {
//            val intent = Intent(this,  Ubicacion::class.java)
//
//            intent.putExtra("Coo",  (latitud + "/" +longitud)  )
//            startActivity(intent)
//        }

        val url = "https://gist.githubusercontent.com/claudialegrec/0328c8178834d9339cbe32a444460520/raw/3de60c7ab163e079ec80234ac27fa97c14a93479/UserProfile.json"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET, url, Response.Listener { response ->
                Log.d("response","La respuesta es ${response}")

                val contact = Gson().fromJson(response, UserInfo::class.java)

                etNombre.setText(contact.nombre)
                etApellidos.setText(contact.apellidos)
                etTelefono.setText(contact.telefono)
                etEmail.setText(contact.email)
                foto = contact.imagenid

                val profilePicture = foto
                val base64Image: String = profilePicture!!.split(",").get(1)
                val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
                val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                userFoto.setImageBitmap(decodedByte)
            },
            Response.ErrorListener {
                Log.d("error", "Algo salio mal")
            })
        queue.add(stringRequest)
    }
}