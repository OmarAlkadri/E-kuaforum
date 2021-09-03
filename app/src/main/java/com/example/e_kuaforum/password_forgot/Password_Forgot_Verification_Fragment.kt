package com.example.e_kuaforum.password_forgot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentPasswordForgotBinding
import com.example.e_kuaforum.databinding.FragmentPasswordForgotVerificationBinding

class Password_Forgot_Verification_Fragment : Fragment() {

    lateinit var Binding: FragmentPasswordForgotVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_password__forgot__verification_, container, false)

        Binding.nextBtn.setOnClickListener{
            findNavController().navigate(R.id.rest_Password_Fragment)
        }

        return Binding.root
    }

}