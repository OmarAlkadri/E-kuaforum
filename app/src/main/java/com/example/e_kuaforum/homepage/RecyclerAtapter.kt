package com.example.e_kuaforum.homepage

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.e_kuaforum.Models.User_Singleton
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.PostItemBinding
import kotlinx.android.synthetic.main.coment_page_dialog.view.*
import org.json.JSONArray
import org.json.JSONObject

class RecyclerAtapter(title:ArrayList<Post>):RecyclerView.Adapter<RecyclerAtapter.ViewHolder>() {

    private var postItems:ArrayList<Post>
    private lateinit var context:Context
    lateinit var mDialogView:View
    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<Comment_RecyclerAtapter.ViewHolder>? = null

    init {
        this.postItems = title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAtapter.ViewHolder {
        val binding = PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerAtapter.ViewHolder, position: Int) {
        holder.BarberName.text = postItems[position].getBarber()
        holder.PostText.text = postItems[position].getPostText()
      //  holder.post_image.setImageResource(postItems[position].getPostImag_Video())
        //holder.comment.text = postItems[position].getComment()
        holder.likes.text = postItems[position].getLike().toString()


        holder.likesImg.setOnClickListener {
            addLike(context,postItems[position].getPostId())
        }

        holder.commentImg.setOnClickListener {
            Dialog(postItems[position].getComment())
            mDialogView.button2.setOnClickListener {
                if(mDialogView.CommentText.text.toString() != "" && mDialogView.CommentText.text.toString() != " ")
                    addComment(context,postItems[position].getPostId(),mDialogView.CommentText.text.toString())
            }

        }
    }

    override fun getItemCount() = postItems.size


    fun Dialog(array:JSONArray){
        mDialogView = LayoutInflater.from(context).inflate(R.layout.coment_page_dialog, null)
        val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        layoutManager = LinearLayoutManager(context)
        mDialogView.commentsrecy.layoutManager = layoutManager




        mDialogView.goBack.setOnClickListener {
            mAlertDialog.dismiss()
        }

        adapter = Comment_RecyclerAtapter(array)
        mDialogView.commentsrecy.adapter = adapter
        adapter?.notifyDataSetChanged()
    }


    fun addLike(context:Context,entityPostId:Int){
        val obj = JSONObject()
        obj.put("UserId", User_Singleton.getInstance().getUserId())
        obj.put("entityPostId", entityPostId)
        val objRequest = JsonObjectRequest(
            Request.Method.POST, "https://10.0.2.2:44307/api/Likes", obj,
            Response.Listener { response ->

            },
            Response.ErrorListener { error ->
                println("erros is: %s".format(error))
            }
        )
        Volley.newRequestQueue(context).add(objRequest)
    }

    fun addComment(context:Context,entityPostId:Int,coment:String){
        val obj = JSONObject()
        obj.put("UserId", User_Singleton.getInstance().getUserId())
        obj.put("entityPostId", entityPostId)
        obj.put("comments1", coment)

        val objRequest = JsonObjectRequest(
            Request.Method.POST, "https://10.0.2.2:44307/api/Comments", obj,
            Response.Listener { response ->

            },
            Response.ErrorListener { error ->
                println("erros is: %s".format(error))
            }
        )
        Volley.newRequestQueue(context).add(objRequest)
    }

    inner class ViewHolder(binding:PostItemBinding):RecyclerView.ViewHolder(binding.root){
        var BarberName:TextView
        var PostText:TextView
        var post_image:ImageView
        var commentImg:ImageView
        var comment:TextView
        var likes:TextView
        var likesImg:ImageView
        init {
            BarberName = binding.BarberName
            PostText = binding.PostText
            post_image = binding.postImage
            comment = binding.comments
            likes = binding.likes
            likesImg = binding.likeImg
            commentImg = binding.comment
        }
    }
}