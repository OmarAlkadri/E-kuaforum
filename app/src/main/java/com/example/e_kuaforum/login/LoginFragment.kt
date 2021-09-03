package com.example.e_kuaforum.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_kuaforum.Models.NukeSSLCerts
import com.example.e_kuaforum.Models.User_Singleton
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentLoginBinding
import com.example.e_kuaforum.homepage.HomePage
import com.example.e_kuaforum.rendezvous_page.RendezvousFragment
import com.example.e_kuaforum.sign_up.SignUpActivity
import kotlinx.android.synthetic.main.fragment_sign_up.view.*
import java.time.LocalDateTime
import java.time.temporal.TemporalAdjusters.lastDayOfMonth
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var a = NukeSSLCerts()
        a.nuke()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.logInBtn.setOnClickListener{
           Toast.makeText(getActivity(), "giris yapildi.", Toast.LENGTH_SHORT).show()
           // LogIn()

            val intent = Intent(getActivity(), HomePage::class.java)
            User_Singleton.getInstance().setUserId(1)
            startActivity(intent)

        }

        binding.forgotPasswordBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_passwordForgotFragment)
        }


        binding.newUserBtn.setOnClickListener{
            val intent = Intent(getActivity(), SignUpActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

    fun LogIn(){
        val queue = Volley.newRequestQueue(getActivity()?.getApplicationContext())
        val url = "https://10.0.2.2:44307/api/Users/" + binding.editTextEmail.text.toString() + "/"+  binding.editTextPassword.text.toString()
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->
                var Id = response.toInt()
                if(Id > 0){
                    val intent = Intent(getActivity(), HomePage::class.java)
                    User_Singleton.getInstance().setUserId(Id)
                    startActivity(intent)
                }
                else{
                   // Toast.makeText(context, "kulanci adi veya sifre yanlis.", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                println("erros is: %s".format(error))
            }
        )
        queue.add(stringRequest)
    }
}