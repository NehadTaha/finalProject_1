package com.example.finalproject.Domain

import java.io.Serializable
class FoodDomain(
    var title: String,
    var description: String,
    var picUrl: String,
    var price: Double,
    var time: Int,
    var energy: Int,
    var score: Double,
    var numberinCart: Int
) : Serializable
