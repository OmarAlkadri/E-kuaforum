package com.example.e_kuaforum.sign_up

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.e_kuaforum.Models.NukeSSLCerts
import com.example.e_kuaforum.Models.User_Singleton
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentSignUpBinding
import com.example.e_kuaforum.homepage.HomePage
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SignUpFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding : FragmentSignUpBinding
    private lateinit var ViewModel : SignUpViewModel

    lateinit var PersonMail :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var a = NukeSSLCerts()
        a.nuke()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        ViewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)
        AutoGenderList()


        binding.cirRegisterBtn.setOnClickListener {
            if(CheckData()){
                PersonMail = binding.editTextEmail.text.toString()

            //    PersonMail = "omar.omar@gmail.com"



                SetJsonVariablePerson()
                User_Singleton.getInstance().setage(binding.editTextage.text.toString().toInt())
                User_Singleton.getInstance().setuserName(binding.editUserName.text.toString())


            getPersonId()

               findNavController().navigate(R.id.action_signUpFragment_to_lodingFragment)
            }
        }
        return binding.root
    }

    fun AutoGenderList(){
        var a : AutoCompleteTextView = binding.editTextGender
        var arryList : ArrayList<String> = ArrayList()
        arryList.add("Erkek")
        arryList.add("Kadın")
        var arryAdaptar : ArrayAdapter<String>? =
            getActivity()?.let {
                ArrayAdapter(it,R.layout._menu,arryList)
            }
        a.setAdapter(arryAdaptar)
        a.setThreshold(1)
    }

    fun CheckData():Boolean{
        if(binding.editTextName.text.toString().length < 1) {
            binding.editTextName.setError("bos gecmaz ....")
            return false
        }
        if(binding.editUserName.text.toString().length < 1) {
            binding.editUserName.setError("bos gecmaz ....")
            return false
        }
        if(binding.editTextSurName.text.toString().length < 1) {
            binding.editTextSurName.setError("bos gecmaz ....")
            return false
        }
        if(binding.editTextGender.text.toString().length < 1) {
            binding.editTextGender.setError("bos gecmaz ....")
            return false
        }
        if(binding.editTextEmail.text.toString().length < 1) {
            binding.editTextEmail.setError("bos gecmaz ....")
            return false
        }
        else if(!ViewModel.Check_Maill(binding.editTextEmail.text.toString())) {
            binding.editTextEmail.setError("Email ....")
            return false
        }
        if(binding.editTextPassword.text.toString().length < 1) {
            binding.editTextPassword.setError("bos gecmaz ....")
            return false
        }
        else  if(!ViewModel.Check_Password(binding.editTextPassword.text.toString())) {
            binding.editTextPassword.setError("Password is low")
            return false
        }
        if(binding.editTextMobile.text.toString().length < 1) {
            binding.editTextMobile.setError("bos gecmaz ....")
            return false
        }
        else if(!ViewModel.Check_Phone(binding.editTextMobile.text.toString())) {
            binding.editTextMobile.setError("Mobile ....")
            return false
        }
        return true
    }

    fun SetJsonVariablePerson(){
        val queue = Volley.newRequestQueue(getActivity()?.getApplicationContext())

        val obj = JSONObject()
        obj.put("personnelName", binding.editTextName.text.toString())
        obj.put("personnelSurname", binding.editTextSurName.text.toString())

        var data = binding.editTextGender.text.toString()
        if(data=="Erkek")
            obj.put("personelGender", 1)
        else if(data=="Kadın")
            obj.put("personelGender", 2)

        obj.put("personnelMaill", PersonMail)
        obj.put("personnelPassword", binding.editTextPassword.text.toString())
        obj.put("personnelPhone", binding.editTextMobile.text.toString())
        obj.put("personnelImageUrl", binding.editTextMobile.text.toString())
        val objRequest = JsonObjectRequest(Request.Method.POST, "https://10.0.2.2:44307/api/Personnels", obj,
            Response.Listener { response ->

            },
            Response.ErrorListener { error ->
                println("erros is: %s".format(error))
            }
        )
        queue.add(objRequest)
    }

    fun getPersonId(){
        val queue = Volley.newRequestQueue(getActivity()?.getApplicationContext())
        val url = "https://10.0.2.2:44307/api/Personnels" +"/"+ PersonMail.toString()
        val stringRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
              User_Singleton.getInstance().setPersonelId(response.getInt("personnelId").toInt())
            },
            Response.ErrorListener { error ->
                println("erros is: %s".format(error))
            }
        )
        queue.add(stringRequest)
    }
}