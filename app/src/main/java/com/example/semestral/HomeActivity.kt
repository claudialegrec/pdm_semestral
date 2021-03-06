package com.example.semestral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class HomeActivity : AppCompatActivity() {

    lateinit var Listweb: ListView
    private lateinit var userInfoBtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userInfoBtn = findViewById(R.id.userInfoBtn)

        userInfoBtn.setOnClickListener {
            var intent = Intent(this, UserProfileActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Informacion del usuario", Toast.LENGTH_LONG).show()
        }

        Listweb = findViewById(R.id.listViewWebService)
        var url = "https://gist.githubusercontent.com/AlfredoGarciaYapor/dbf3334ed5f95fcc4972e6ea89f4ad1b/raw/bd1aa3a18fd8f34700cf9e69a30945e4892b1ec7/examenFinalpdm.json"
        val queue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET, url, Response.Listener { response ->

                Log.d("response","La respuesta es ${response}")

                val peoplelist = Gson().fromJson(response, PeopleInfo::class.java).people

                Log.d("Name", "${peoplelist[0].name}")

                val adapter = peopleAdpter(this, peoplelist)

                Listweb.adapter = adapter

            },
            Response.ErrorListener {

            })
        queue.add(stringRequest)
    }
}