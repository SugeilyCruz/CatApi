package com.example.catapi.controler

import com.example.catapi.model.CatResponse

interface CatsClickListener {
    fun onClick(cat: CatResponse)
}