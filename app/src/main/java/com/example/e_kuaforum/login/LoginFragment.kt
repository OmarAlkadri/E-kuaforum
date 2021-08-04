package com.example.e_kuaforum.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.logInBtn.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_lodingFragment)
            if(goToHome())
            {
                findNavController().navigate(R.id.action_loginFragment_to_homePageFragment)

            }
        }

        binding.forgotPasswordBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_passwordForgotFragment)
        }


        binding.newUserBtn.setOnClickListener{
            goToSingUp()
        }

        return binding.root
    }
    fun goToHome(): Boolean {
        return true
    }
    fun goToSingUp(){
        findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
    }

}