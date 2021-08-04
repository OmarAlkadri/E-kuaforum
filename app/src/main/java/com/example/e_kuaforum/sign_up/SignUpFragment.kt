package com.example.e_kuaforum.sign_up

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentSignUpBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {



        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)


        var a : AutoCompleteTextView = binding.editTextGender

        var arryList : ArrayList<String> = ArrayList()
        arryList.add("Erkek")
        arryList.add("Kadın")

        var arryAdaptar : ArrayAdapter<String>? =
            getActivity()?.let { ArrayAdapter(it,R.layout.gender_menu,arryList) }

        a.setAdapter(arryAdaptar)

        a.setThreshold(1)





        return binding.root
    }

    fun Siginup(binding:FragmentSignUpBinding)
    {

       /* val queue = MySingleton.getInstance(this.applicationContext).requestQueue
        val textView = binding.editTextName
        val url = "https://10.0.2.2:44307/api/Barbers"
        val addUserurl = "https://10.0.2.2:44307/api/Barbers"

        val obj = JSONObject()
        obj.put("userId", "20000000")
        obj.put("userGender", false)

        val POSTjsonObjectRequest = JsonObjectRequest(Request.Method.POST, addUserurl, obj,
            Response.Listener { response ->
                textView.text = "Ok"
            },
            Response.ErrorListener { error ->
                println("Response: %s".format(error))
            }

        )


        */
      /*  val jsonObjectRequest = JsonArrayRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                var a = response.getJSONObject(1)
                try {
                    var b = a.getString("barberShowName")

                } catch (e: NullPointerException) {
                    Log.e("Class name goes here", "NPE occured at $e")
                } catch (e: Exception) {
                    Log.e("Class name goes here", "Exception occured at $e")
                }


                textView.text = "Response: %s".format(a.getString("barberShowName"))
            },
            Response.ErrorListener { error ->
                println("Response: %s".format(error))
            }
        )
        */

        // Access the RequestQueue through your singleton class.
   //     MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SignUpFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SignUpFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}