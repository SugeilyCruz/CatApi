package com.example.catapi.controler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.catapi.R
import com.example.catapi.model.CatResponse

class catAdapter(
    private var cats: List<CatResponse>,
    private var clickLister: CatsClickListener
): RecyclerView.Adapter<CatViewHolder>(){

    //: - que tipo de dato recibe o retorna
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        var LayoutInflater = LayoutInflater.from(parent.context)
        return CatViewHolder( LayoutInflater.inflate(R.layout.item_cat, parent, false ), clickLister )
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) { //anexamos cosas
        val item = cats[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return cats.size
    }
}