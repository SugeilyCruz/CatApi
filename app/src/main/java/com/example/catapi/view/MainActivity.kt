package com.example.catapi.view

import android.annotation.SuppressLint
import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.OpenableColumns
import android.widget.GridLayout
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.catapi.R
import com.example.catapi.controler.CatsClickListener
import com.example.catapi.controler.catAdapter
import com.example.catapi.databinding.ActivityMainBinding
import com.example.catapi.model.ApiCatService
import com.example.catapi.model.CatResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), CatsClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: catAdapter
    private val catData = mutableListOf<CatResponse>()

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater) //solito lo crea
        setContentView(binding.root) //Forma alternativa de inflar las vistas

        getData()
        setUpRv()
    }

    private fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create()) //gSON ES POR google que no lee json
            .build()
    }


    private fun getData(){
        CoroutineScope(Dispatchers.IO).launch {
            val retrofitCall = getRetrofit()
                .create(ApiCatService::class.java)
                .getCats("breeds?limit=50&page=0&api_key=live_JVWsmJBlJMUdv3jv3lFD5lSdIvN2EmOsUNCkda8hZyAiLTSglkOznOAWTkhiHKhR")

            val res: List<CatResponse>? = retrofitCall.body() //si le llega a llegar null no truenen

            runOnUiThread{
                if(retrofitCall.isSuccessful){
                    val cats: List<CatResponse> = res ?: emptyList()

                    if (cats.isNotEmpty()){
                       catData.addAll(cats)
                    }
                    adapter.notifyDataSetChanged()
                }
            }// correr en la vista de usuario
        }//hacer al mismo tiempo, se ejecuta en otro hilo del trabajo
    }

    private fun setUpRv(){
        adapter = catAdapter(catData, this) //adapter - rellena con los item_cat
        binding.rvCats.layoutManager = GridLayoutManager(this, 2) //padre de nuestro Rv, pone las dos columnas.
        binding.rvCats.adapter = adapter
    }

    override fun onClick(cat: CatResponse) {
       // Toast.makeText(this,cat.catId, Toast.LENGTH_SHORT).show() // imprime el catId
        val detailIntent = Intent(this, CatDetail::class.java)
        detailIntent.putExtra("imgId", cat.imgId)
        detailIntent.putExtra("catId", cat.catId)
        detailIntent.putExtra("catName", cat.catName)
        detailIntent.putExtra("description", cat.description)
        detailIntent.putExtra("wikipediaurl", cat.wikipediaurl)
        startActivity(detailIntent)
    }



}