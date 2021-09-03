package com.example.e_kuaforum.payment_page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentPaymentTypeBinding

class Payment_Type_Fragment : Fragment() {

    lateinit var Binding:FragmentPaymentTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment_type, container, false)

        Binding.banklaBtn.setOnClickListener{
            findNavController().navigate(R.id.action_payment_Type_Fragment2_to_paymentFragment)
        }

        Binding.banklaBtn.setOnClickListener{
            findNavController().navigate(R.id.action_payment_Type_Fragment2_to_paymentFragment)
        }

        return Binding.root
    }

}