package com.example.e_kuaforum.homepage

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.e_kuaforum.Models.User_Singleton
import com.example.e_kuaforum.databinding.ComentItemBinding
import com.example.e_kuaforum.databinding.ComentPageDialogBinding
import com.example.e_kuaforum.databinding.PostItemBinding
import org.json.JSONArray
import org.json.JSONObject

    class Comment_RecyclerAtapter(title:JSONArray):RecyclerView.Adapter<Comment_RecyclerAtapter.ViewHolder>() {

    private var postItems:JSONArray
    private lateinit var context:Context

    init {
        this.postItems = title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Comment_RecyclerAtapter.ViewHolder {
        val binding = ComentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: Comment_RecyclerAtapter.ViewHolder, position: Int) {
        holder.coment.text = postItems.getJSONObject(position).getString("text")
    }

    override fun getItemCount() = postItems.length()

    inner class ViewHolder(binding:ComentItemBinding):RecyclerView.ViewHolder(binding.root){
        var coment:TextView

        init {
            coment = binding.Coment
        }
    }
}