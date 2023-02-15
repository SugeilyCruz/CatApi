package com.example.catapi.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.catapi.R
import com.example.catapi.databinding.ActivityCatDetailBinding
import com.example.catapi.databinding.ItemCatBinding
import com.example.catapi.model.ApiCatService
import com.example.catapi.model.CatResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatDetail : AppCompatActivity() {

    private lateinit var binding: ActivityCatDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityCatDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)//raiz


        setUp()
    }

    private fun setUp(){
        val imgId = intent.getStringExtra("imgId")
        val catName = intent.getStringExtra("catName")
        val description = intent.getStringExtra("description")
        val temperament = intent.getStringExtra("temperament")
        val wikipediaurl = intent.getStringExtra("wikipediaurl")


        Picasso.get().load("https://cdn2.thecatapi.com/images/$imgId.jpg").into(binding.ivCatImage)
        binding.tvName.text = catName
        binding.tvTemp.text = temperament
        binding.tvDesc.text = description

        binding.btnWikipedia.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(wikipediaurl)
            startActivity(intent)
        }
    }

}