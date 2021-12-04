package com.example.semestral

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity

class peopleAdpter (private val mContext: Context, private val listPeople: List<PeopleModel>):
    ArrayAdapter<PeopleModel>(mContext, 0, listPeople)
{

    private lateinit var itemName: TextView
    private lateinit var itemPhone: TextView
    private lateinit var itemDescription: TextView
    private lateinit var itemEmail: TextView
    private lateinit var itemAddress: TextView
    private lateinit var imageView: ImageView
    private lateinit var gpsButton: Button

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Se manda llamar el layout para cada uno de los items
        val layout = LayoutInflater.from(mContext).inflate(R.layout.people_item, parent, false)
        val person = listPeople[position]
        itemName = layout.findViewById(R.id.itemName)
        itemPhone = layout.findViewById(R.id.itemPhone)
        itemEmail = layout.findViewById(R.id.itemEmail)
        itemAddress = layout.findViewById(R.id.itemAddress)
        itemDescription = layout.findViewById(R.id.itemDescription)
        imageView = layout.findViewById(R.id.imageViewPeople)
        gpsButton = layout.findViewById(R.id.gpsBtn)
        itemName.text = person.name


            val profilePicture = person.picture
//            val latitud = person.latitud
//            val longitud = person.longitud
//            val base64Image: String = profilePicture.split(",").get(1)
//            val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
//            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

//            imageView.setImageBitmap(decodedByte)

        itemName.text = person.name
        itemPhone.text = person.phone
        itemDescription.text = person.description
        itemEmail.text = person.email
        itemAddress.text = person.address


        return layout
    }
}
