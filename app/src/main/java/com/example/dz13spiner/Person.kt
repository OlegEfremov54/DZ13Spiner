package com.example.dz13spiner

class Person(
    val name: String,
    val surName:String,
    val age: String,
    val role: String){
    override fun toString(): String {
        return "$name, $age"
    }
}