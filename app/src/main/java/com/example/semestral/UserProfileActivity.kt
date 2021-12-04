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
    private lateinit var ubiBtn: Button

    private var latitud: String? = null
    private var longitud: String? = null
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

        backBtn.setOnClickListener {
            val intent = Intent(this,  HomeActivity::class.java)
            startActivity(intent)
        }

        val url = "https://gist.githubusercontent.com/claudialegrec/0328c8178834d9339cbe32a444460520/raw/bea5ad580f1d3c07f39e122ac63b8abaf2536a8c/UserProfile.json"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET, url, Response.Listener { response ->
                Log.d("response","La respuesta es ${response}")

                val user = Gson().fromJson(response, UserInfo::class.java)

                etNombre.setText(user.nombre)
                etApellidos.setText(user.apellidos)
                etTelefono.setText(user.telefono)
                etEmail.setText(user.email)
                latitud = user.latitud
                longitud = user.longitud
                foto = user.imagenid

                val profilePicture = foto
                val base64Image: String = profilePicture!!.split(",").get(1)
                val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
                val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                userFoto.setImageBitmap(decodedByte)
            },
            Response.ErrorListener {
                Log.d("error", "Algo salio mal")
            })

        ubiBtn.setOnClickListener {
            val intent = Intent(this,  MapsActivity::class.java)

            intent.putExtra("Coo",  (latitud + "/" +longitud)  )
            startActivity(intent)
        }

        queue.add(stringRequest)
    }
}