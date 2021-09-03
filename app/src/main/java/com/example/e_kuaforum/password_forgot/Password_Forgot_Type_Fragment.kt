package com.example.e_kuaforum.password_forgot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentPasswordForgotTypeBinding
import com.example.e_kuaforum.databinding.FragmentPaymentTypeBinding

class Password_Forgot_Type_Fragment : Fragment() {
        lateinit var Binding: FragmentPasswordForgotTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_password__forgot__type_, container, false)

        Binding.maillBtn.setOnClickListener{
            findNavController().navigate(R.id.password_Forgot_Verification_Fragment)

        }
        Binding.phoneBtn.setOnClickListener{
            findNavController().navigate(R.id.password_Forgot_Verification_Fragment)

        }

        return Binding.root
    }

}