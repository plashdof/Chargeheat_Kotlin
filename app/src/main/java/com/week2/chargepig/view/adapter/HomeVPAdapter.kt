package com.week2.chargepig.view.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.week2.chargepig.R
import com.week2.chargepig.databinding.ItemHomeviewBinding

class HomeVPAdapter(val datas : ArrayList<Int>): RecyclerView.Adapter<HomeVPAdapter.ViewHolder>(){

    inner class ViewHolder(private val binding : ItemHomeviewBinding) : RecyclerView.ViewHolder(binding.root){
        val image = binding.ivImg

        fun bind(item:Int){
            Glide.with(itemView)
                .load(item)
                .into(image)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(datas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeviewBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}