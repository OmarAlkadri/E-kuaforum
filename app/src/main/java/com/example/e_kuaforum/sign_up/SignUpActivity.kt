package com.example.e_kuaforum.sign_up

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.JsonObjectRequest
import com.example.e_kuaforum.Models.MySingleton
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentSignUpBinding
import kotlinx.android.synthetic.main.fragment_sign_up.*
import org.json.JSONObject

class SignUpActivity : AppCompatActivity() {

    private lateinit var ViewModel : SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        ViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

    }
}