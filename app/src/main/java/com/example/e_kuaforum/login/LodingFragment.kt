package com.example.e_kuaforum.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.e_kuaforum.MainActivity
import com.example.e_kuaforum.Models.NukeSSLCerts
import com.example.e_kuaforum.Models.User_Singleton
import com.example.e_kuaforum.R
import com.example.e_kuaforum.homepage.HomePage
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LodingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LodingFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var a = NukeSSLCerts()
        a.nuke()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val intent = Intent(getActivity(), MainActivity::class.java)

        SetJsonVariableUser()

        startActivity(intent)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loding, container, false)
    }
    fun SetJsonVariableUser(){
        val obj = JSONObject()
        var age = User_Singleton.getInstance().getage()
        var name = User_Singleton.getInstance().getuserName()
        obj.put("userName", name)
        obj.put("yas",age )
        obj.put("personnelId", User_Singleton.getInstance().getPersonelId())

        val objRequest = JsonObjectRequest(
            Request.Method.POST, "https://10.0.2.2:44307/api/Users", obj,
            Response.Listener { response ->
var a = 5
                var b = a
            },
            Response.ErrorListener { error ->
                println("erros is: %s".format(error))
            }
        )
        Volley.newRequestQueue(getActivity()?.getApplicationContext()).add(objRequest)
    }
}