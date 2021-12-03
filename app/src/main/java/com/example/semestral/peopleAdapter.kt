package com.example.semestral

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class peopleAdpter (private val mContext: Context, private val listPeople: List<PeopleModel>):
    ArrayAdapter<PeopleModel>(mContext, 0, listPeople)
    {

        private lateinit var itemName: TextView
        private lateinit var itemPhone: TextView
        private lateinit var itemDescription: TextView
        private lateinit var imageView: ImageView

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            // Se manda llamar el layout para cada uno de los items
            val layout = LayoutInflater.from(mContext).inflate(R.layout.people_item, parent, false)
            val person = listPeople[position]
            itemName = layout.findViewById(R.id.itemName)
            itemPhone = layout.findViewById(R.id.itemAge)
            itemDescription = layout.findViewById(R.id.itemAdvice)
            imageView = layout.findViewById(R.id.imageViewPeople)
            itemName.text = person.name

            val profilePicture = person.picture
            val base64Image: String = profilePicture.split(",").get(1)
            val decodedString = Base64.decode(base64Image, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)


            imageView.setImageBitmap(decodedByte)

            itemName.text = person.name
            itemPhone.text = person.description
            itemDescription.text = person.address


            return layout
        }
    }
