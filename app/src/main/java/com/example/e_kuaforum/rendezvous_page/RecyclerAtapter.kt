package com.example.e_kuaforum.rendezvous_page

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.BarberShowItemBinding
import com.example.e_kuaforum.databinding.PostItemBinding
import com.example.e_kuaforum.databinding.RendezvouItemBinding
import com.example.e_kuaforum.homepage.Post

class RecyclerAtapter(title:ArrayList<ReservationShow>): RecyclerView.Adapter<RecyclerAtapter.ViewHolder>()
{
    private var postItems:ArrayList<ReservationShow>

    init {
        this.postItems = title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAtapter.ViewHolder
    = ViewHolder(RendezvouItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: RecyclerAtapter.ViewHolder, position: Int) {
        holder.BarberName.text = postItems[position].getBarberShowName()
        holder.BarberAdres.text = postItems[position].getAdres()
        holder.reservationTime.text = postItems[position].getReservationTime()
        holder.BarberImag.setImageResource(R.drawable.app)
    }

    override fun getItemCount() = postItems.size

    inner class ViewHolder(binding: RendezvouItemBinding): RecyclerView.ViewHolder(binding.root){
        var BarberName: TextView
        var BarberAdres: TextView
        var reservationTime: TextView
        var BarberImag: ImageView
        init {
            BarberName = binding.BarberName
            BarberAdres = binding.BarberAdres
            reservationTime = binding.WorkingHours
            BarberImag = binding.BarberImag
       }
    }
}