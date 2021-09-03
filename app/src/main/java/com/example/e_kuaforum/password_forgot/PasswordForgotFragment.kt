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
import com.example.e_kuaforum.databinding.FragmentPasswordForgotTypeBinding

class PasswordForgotFragment : Fragment() {
    lateinit var Binding: FragmentPasswordForgotBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_password_forgot, container, false)

        Binding.nextBtn.setOnClickListener{
            Binding.emailText.text
            findNavController().navigate(R.id.password_Forgot_Type_Fragment)
        }

        return Binding.root
    }
}