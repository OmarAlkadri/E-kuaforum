package com.example.e_kuaforum.barbershop_show_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.e_kuaforum.Models.Barber_Singleton
import com.example.e_kuaforum.Models.NukeSSLCerts
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentBarberShowBinding
import org.json.JSONException
import org.json.JSONObject

class BarberShowFragment : Fragment() {

    lateinit var Binding: FragmentBarberShowBinding
    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<RecyclerAtapter.ViewHolder>? = null
    lateinit var BarberShowItems : ArrayList<barberShop>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var a = NukeSSLCerts()
        a.nuke()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

      Binding = inflate(inflater, R.layout.fragment_barber_show, container, false)



        layoutManager = LinearLayoutManager(context)
        Binding.recyclerViewBarber.layoutManager = layoutManager
        JsonToAdapter()


        return Binding.root
    }
    fun JsonToAdapter(){
        val url = "https://10.0.2.2:44307/api/Barbers"

        lateinit var Jo: JSONObject
        lateinit var BarberShowName:String
        lateinit var BarberImg:String
        lateinit var contactInfo:String
        lateinit var WorkingHours:String
        BarberShowItems = ArrayList<barberShop>()

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                for (i in 1..response.length()) {
                    try {
                        Jo = response.getJSONObject(i - 1)

                        if (Jo.getString("barberShowName").length > 0) {
                            BarberShowName = Jo.getString("barberShowName")
                            if(Barber_Singleton.getInstance().getBarberName()!=null)
                            Barber_Singleton.getInstance().setBarberName(BarberShowName)
                        }
                        else
                            BarberShowName = "null"

                        if (Jo.getString("barberImg").length > 0)
                            BarberImg = Jo.getString("barberImg")
                        else
                            BarberImg = "null"

                        if (Jo.getString("openingTime").length > 0&&Jo.getString("closingTime").length > 0)
                            WorkingHours = Jo.getInt("openingTime").toString() +" - " + Jo.getInt("closingTime").toString()
                        if (Jo.getJSONObject("contactInfoModel").getString("adres").length > 0)
                            contactInfo = Jo.getJSONObject("contactInfoModel").getString("adres")

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    } catch (e: VolleyError) {
                        e.printStackTrace()
                    }
                    BarberShowItems.add(barberShop(BarberShowName,contactInfo,WorkingHours,BarberImg))
                }
                adapter = RecyclerAtapter(BarberShowItems)
                Binding.recyclerViewBarber.adapter = adapter
                adapter?.notifyDataSetChanged()
            },
            Response.ErrorListener { error: VolleyError? ->
                println("Response: %s".format(error))
            }
        )
        Volley.newRequestQueue(getActivity()?.getApplicationContext()).add(jsonObjectRequest)
    }
}