package com.example.e_kuaforum.homepage

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.e_kuaforum.Models.NukeSSLCerts
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentHomePageBinding
import kotlinx.android.synthetic.main.coment_page_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home_page.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class HomePageFragment : Fragment() {

    lateinit var Binding : FragmentHomePageBinding
    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<RecyclerAtapter.ViewHolder>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var a = NukeSSLCerts()
        a.nuke()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)

        layoutManager = LinearLayoutManager(context)
        Binding.recyclerViewPosts.layoutManager = layoutManager
        getJson()

        return Binding.root
    }



    fun getJson(){

        val url = "https://10.0.2.2:44307/api/EntityPosts"
        var Jo: JSONObject
        var PostId: Int = 0
        lateinit var PostText: String
        lateinit var PostTime: String
        lateinit var BarberName: String
        lateinit var ImgVideoUrl: String
        lateinit var comment: JSONArray
        var likes: Int = 0
        val PostItems = ArrayList<Post>()

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                for (i in 1..response.length()) {
                    try {
                        if(response.length()>0) {
                            Jo = response.getJSONObject(i - 1)

                            PostId = Jo.getInt("entityPostId")

                            if (Jo.getString("entityPostText").length > 0)
                                PostText = Jo.getString("entityPostText")
                            else
                                PostText = "null"
                            if (Jo.getString("entityPostTime").length > 0)
                                PostTime = Jo.getString("entityPostTime")
                            else
                                PostTime = "null"
                            if (Jo.getString("entityImgVideoUrl").length > 0)
                                ImgVideoUrl = Jo.getString("entityImgVideoUrl")
                            else
                                ImgVideoUrl = "null"
                            if (Jo.getJSONObject("barber").length() > 0)
                                BarberName = Jo.getJSONObject("barber").getString("name")
                            else
                                BarberName = "null"

                                comment = Jo.getJSONArray("commentModels")


                            likes = Jo.getInt("likes")
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    } catch (e: VolleyError) {
                        e.printStackTrace()
                    }
                    PostItems.add(Post(PostId,PostText,PostTime,BarberName,ImgVideoUrl,comment,likes))
                }
                adapter = RecyclerAtapter(PostItems)
                Binding.recyclerViewPosts.adapter = adapter
                adapter?.notifyDataSetChanged()
            },
            Response.ErrorListener { error: VolleyError? ->
                println("Response: %s".format(error))
            }
        )
        Volley.newRequestQueue(getActivity()?.getApplicationContext()).add(jsonObjectRequest)
    }
}