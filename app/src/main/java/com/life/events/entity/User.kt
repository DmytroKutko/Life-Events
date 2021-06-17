package com.life.events.entity

data class User(
    val _id: String,
    val about: String,
    val birth: Birth,
    val death: Death?,
    val gender: String,
    val name: String,
    val picture: String
)