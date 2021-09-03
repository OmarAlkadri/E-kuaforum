package com.example.e_kuaforum.barbershop_show_page

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.e_kuaforum.databinding.BarberShowItemBinding
import com.example.e_kuaforum.databinding.PostItemBinding
import com.example.e_kuaforum.homepage.Post

class RecyclerAtapter(title:ArrayList<barberShop>): RecyclerView.Adapter<RecyclerAtapter.ViewHolder>()
{
    private var postItems:ArrayList<barberShop>

    init {
        this.postItems = title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAtapter.ViewHolder
    = ViewHolder(BarberShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: RecyclerAtapter.ViewHolder, position: Int) {
        holder.BarberName.text = postItems[position].getBarberShowName()
        holder.BarberAdres.text = postItems[position].getAdres()
        holder.WorkingHours.text = postItems[position].getWorkingHours()
        //  holder.post_image.setImageResource(postItems[position].getPostImag_Video())
    }

    override fun getItemCount() = postItems.size

    inner class ViewHolder(binding: BarberShowItemBinding): RecyclerView.ViewHolder(binding.root){
        var BarberName: TextView
        var BarberAdres: TextView
        var BarberImag: ImageView
        var WorkingHours: TextView
        init {
            BarberName = binding.BarberName
            BarberAdres = binding.BarberAdres
            WorkingHours = binding.WorkingHours
            BarberImag = binding.BarberImag
        }
    }
}