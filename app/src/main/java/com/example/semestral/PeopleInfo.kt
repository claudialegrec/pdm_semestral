package com.example.semestral

class PeopleInfo (
    val people: List<PeopleModel>
)
data class PeopleModel(
    val name: String, val description: String, val address: String, val picture: String, val phone: String, val email: String, val longitud: String, val latitud: String
)