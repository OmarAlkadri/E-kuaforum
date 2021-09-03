package com.example.e_kuaforum.rendezvous_page

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.e_kuaforum.Models.NukeSSLCerts
import com.example.e_kuaforum.R
import com.example.e_kuaforum.databinding.FragmentRendezvousBinding
import kotlinx.android.synthetic.main.rendezvou_dialog.view.*
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList
import android.widget.AdapterView.OnItemClickListener
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.e_kuaforum.Models.Barber_Singleton
import com.example.e_kuaforum.Models.User_Singleton
import com.example.e_kuaforum.barbershop_show_page.BarberShowFragment
import com.example.e_kuaforum.barbershop_show_page.barberShop
import com.example.e_kuaforum.payment_page.PaymentActivity
import com.example.e_kuaforum.sign_up.SignUpActivity
import java.time.DateTimeException
import java.time.LocalDateTime
import java.time.Month
import java.time.temporal.TemporalAdjusters
import java.time.temporal.WeekFields

class RendezvousFragment : Fragment() {

    lateinit var Binding: FragmentRendezvousBinding
    private var layoutManager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<RecyclerAtapter.ViewHolder>? = null
    lateinit var Reservation : ArrayList<ReservationShow>
    lateinit var barber : ArrayList<Barber>
    lateinit var hours : ArrayList<Hours>
    lateinit var mDialogView:View
    var UserId = 0
    var BarberId = 0
    var count :Int = 0
    lateinit var dk_ : ArrayList<Int>
    lateinit var hour_ : ArrayList<Int>

    lateinit var Day:String
    lateinit var Hour:String
    lateinit var Min:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val a = NukeSSLCerts()
        a.nuke()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rendezvous, container, false)
        UserId = User_Singleton.getInstance().getUserId()
        JsonToAdapter()
        rezPage()
        return Binding.root
    }

    fun rezPage(){
        Binding.ReservationBtn.setOnClickListener {
            mDialogView = LayoutInflater.from(getActivity()).inflate(R.layout.rendezvou_dialog, null)
            val mBuilder = AlertDialog.Builder(context).setView(mDialogView)
            val mAlertDialog = mBuilder.show()
            AutoListForNames()

            mDialogView.barbername.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
                mDialogView.day.setText("")
                mDialogView.hour.setText("")
                mDialogView.dk.setText("")
                getBarberId(mDialogView.barbername.text.toString())
                AutoList_Days()
            })

            mDialogView.day.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
                mDialogView.hour.setText("")
                mDialogView.dk.setText("")
                if(mDialogView.day.text.toString() == "Monday")
                    AutoList_Hour(1)
                else if(mDialogView.day.text.toString() == "Tuesday")
                    AutoList_Hour(2)
                else if(mDialogView.day.text.toString() == "Wednesday")
                    AutoList_Hour(3)
                else if(mDialogView.day.text.toString() == "Thursday")
                    AutoList_Hour(4)
                else if(mDialogView.day.text.toString() == "Friday")
                    AutoList_Hour(5)
                else if(mDialogView.day.text.toString() == "Saturday")
                    AutoList_Hour(6)
            })

            mDialogView.hour.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
                mDialogView.dk.setText("")
                var bol = true
                for(i in 0..hour_.size-1)
                {
                    if(mDialogView.hour.text.toString() == hour_[i].toString()){
                        AutoList_Min(false,i)
                        bol = false
                    }
                    else
                        bol = true
                }
                if(bol)
                    AutoList_Min(true)
                count = 0
            })


            mDialogView.rendezvouBtn.setOnClickListener {
                if(CheckData()){
                    Day = mDialogView.day.text.toString()
                    Hour = mDialogView.hour.text.toString()
                    Min = mDialogView.dk.text.toString()
                    setJeson(mDialogView.textInputSurName.text.toString(),Day,Hour,Min)
                    mAlertDialog.dismiss()

                    val intent = Intent(getActivity(), PaymentActivity::class.java)
                    startActivity(intent)
                }
            }
            mDialogView.dragDown.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
    }
    fun CheckData():Boolean{
        if(mDialogView.barbername.text.toString().length < 1) {
            mDialogView.barbername.setError("bos gecmaz ....")
            return false
        }
        if(mDialogView.day.text.toString().length < 1) {
            mDialogView.day.setError("bos gecmaz ....")
            return false
        }
        if(mDialogView.hour.text.toString().length < 1) {
            mDialogView.hour.setError("bos gecmaz ....")
            return false
        }
        if(mDialogView.dk.text.toString().length < 1) {
            mDialogView.dk.setError("bos gecmaz ....")
            return false
        }
        return true
    }

    fun getBarberId(name:String) {
        barber.forEach()
        {
            if(it.Name == name) {
                BarberId = it.Id
                return@forEach
            }
        }
    }

    fun setJeson(tip:String,Day:String,Hour:String,Min:String){
        val obj = JSONObject()

        obj.put("userId", UserId)
        obj.put("Day",Day)
        obj.put("Hour",Hour)
        obj.put("Min",Min)
        obj.put("barberId", BarberId)
        obj.put("reservationType","sac")
        obj.put("reservationStatus",false)

        val objRequest = JsonObjectRequest(Request.Method.POST, "https://10.0.2.2:44307/api/ReservationBarbers", obj,
            Response.Listener { response ->

            },
            Response.ErrorListener { error ->
                println("erros is: %s".format(error))
            }
        )
        Volley.newRequestQueue(getActivity()?.getApplicationContext()).add(objRequest)
    }
    fun JsonToAdapter(){
        val url = "https://10.0.2.2:44307/api/ReservationBarbers"
        lateinit var Jo: JSONObject
        lateinit var BarberShowName:String
        lateinit var BarberImg:String
        var contactInfo:String = "bhj "
        var day = 0
        var hour = 0
        var min = 0
        Reservation = ArrayList<ReservationShow>()
        var paid:Int = 0
        layoutManager = LinearLayoutManager(getActivity()?.getApplicationContext())
        layoutManager = LinearLayoutManager(context)
        Binding.ReservationRec.layoutManager = layoutManager
        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                for (i in 1..response.length()) {
                    try {
                        Jo = response.getJSONObject(i - 1)
                        BarberId = Jo.getInt("barberId")
                        if (Jo.getString("barberShowName").length > 0)
                            BarberShowName = Jo.getString("barberShowName")
                        else
                            BarberShowName = "null"
                        if (Jo.getString("barberImg").length > 0)
                            BarberImg = Jo.getString("barberImg")
                        else
                            BarberImg = "null"

                        day = Jo.getInt("day")
                        hour = Jo.getInt("hour")
                        min = Jo.getInt("min")

                        if (Jo.getJSONObject("payingOffModel").getInt("paid") > 0)
                            paid = Jo.getJSONObject("payingOffModel").getInt("paid")
                        if (Jo.getJSONObject("contactInfoModel").getString("adres").length > 0)
                            contactInfo = Jo.getJSONObject("contactInfoModel").getString("adres")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    } catch (e: VolleyError) {
                        e.printStackTrace()
                    }
                    Reservation.add(ReservationShow(BarberShowName,contactInfo,BarberImg,day,hour.toString(),min.toString(),paid))
                }

                adapter = RecyclerAtapter(Reservation)
                Binding.ReservationRec.adapter = adapter
                adapter?.notifyDataSetChanged()

            },
            Response.ErrorListener { error: VolleyError? ->
                println("Response: %s".format(error))
            }
        )
        Volley.newRequestQueue(getActivity()?.getApplicationContext()).add(jsonObjectRequest)
    }
    fun AutoListForNames() {
        var Name : AutoCompleteTextView = mDialogView.barbername
        var arryList_Name: ArrayList<String> = ArrayList()
        var BarberId: Int = 0
        lateinit var BarberShowName:String
        barber = ArrayList<Barber>()

        lateinit var Jo: JSONObject

        val jsonObjectRequestNames = JsonArrayRequest(
            Request.Method.GET, "https://10.0.2.2:44307/api/Barbers", null,
            Response.Listener { response ->
                for (i in 1..response.length()){
                    try {
                        Jo = response.getJSONObject(i - 1)
                        BarberId = Jo.getInt("barberId")
                        if (Jo.getString("barberShowName").length > 0) {
                            BarberShowName = Jo.getString("barberShowName")
                            arryList_Name.add(BarberShowName)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    } catch (e: VolleyError) {
                        e.printStackTrace()
                    }
                    barber.add(Barber(BarberShowName,BarberId))
                }
            },
            Response.ErrorListener { error: VolleyError? ->
                println("Response: %s".format(error))
            }
        )
        Volley.newRequestQueue(getActivity()?.getApplicationContext()).add(jsonObjectRequestNames)

        var arryAdaptar_arryList_Name = getActivity()?.let {ArrayAdapter(it,R.layout._menu,arryList_Name)}

        Name.setAdapter(arryAdaptar_arryList_Name)
        Name.setThreshold(1)
    }
    fun AutoList_Days(){
        val url = "https://10.0.2.2:44307/api/WorkingHours/"+BarberId.toString()
        var Day : AutoCompleteTextView = mDialogView.day
        var arryList_Day: ArrayList<String> = ArrayList()
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, Response.Listener { response ->
                var days = response.getJSONArray("workingDaysOfWeek")
                for(i in 0..days.length()-1)
                {
                    if(days[i] == 1)
                        arryList_Day.add("Monday")
                    if(days[i] == 2)
                        arryList_Day.add("Tuesday")
                    if(days[i] == 3)
                        arryList_Day.add("Wednesday")
                    if(days[i] == 4)
                        arryList_Day.add("Thursday")
                    if(days[i] == 5)
                        arryList_Day.add("Friday")
                    if(days[i] == 6)
                        arryList_Day.add("Saturday")
                }
            },
            Response.ErrorListener { error: VolleyError? ->
                println("Response: %s".format(error))
            }
        )
        Volley.newRequestQueue(getActivity()?.getApplicationContext()).add(jsonObjectRequest)
        var arryAdaptar_Day = getActivity()?.let {ArrayAdapter(it,R.layout._menu,arryList_Day)}
        Day.setAdapter(arryAdaptar_Day)
        Day.setThreshold(1)
    }
    fun AutoList_Hour(day:Int){
        val url = "https://10.0.2.2:44307/api/WorkingHours/"+BarberId.toString()
        var hour : AutoCompleteTextView = mDialogView.hour
        var arryList_hour : ArrayList<Int> = ArrayList()
        hour_ = ArrayList()
        dk_ = ArrayList()
        lateinit var Jo: JSONObject
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                if (response.getJSONArray("workingHoursOfDay").length() > 0) {
                    var hours = response.getJSONArray("workingHoursOfDay")
                    val jsonObjectRequest_Rez = JsonArrayRequest(
                        Request.Method.GET, "https://10.0.2.2:44307/api/ReservationBarbers", null,
                        Response.Listener { responseT ->
                            for(i in 0..hours.length()-1) {
                                loop@for(j in 0..responseT.length()-1) {
                                    if(j < responseT.length()) {
                                        if(j < responseT.getJSONObject(j).length()){
                                            Jo = responseT.getJSONObject(j)
                                            if (Jo.getInt("day") == day) {
                                                var a = Jo.getInt("hour")
                                                var a1 = hours[i]
                                                if(Jo.getInt("hour") == hours[i])
                                                {
                                                    count++
                                                    if(count == 1)
                                                    {
                                                        hour_.add(hours[i] as Int)
                                                        if(Jo.getInt("min") == 1)
                                                            dk_.add(2)
                                                        else if (Jo.getInt("min") == 2)
                                                            dk_.add(1)
                                                    }
                                                }

                                                if (count==2)
                                                    break@loop
                                            }
                                            else
                                                break@loop
                                        }
                                        else
                                            break@loop
                                    }
                                    else
                                        break@loop
                                }
                                if(count < 2)
                                    arryList_hour.add(hours[i] as Int)
                            }
                        },
                        Response.ErrorListener { error: VolleyError? ->
                            println("Response: %s".format(error))
                        })
                    Volley.newRequestQueue(getActivity()?.getApplicationContext()).add(jsonObjectRequest_Rez)
                }
            },
            Response.ErrorListener { error: VolleyError? ->
                println("Response: %s".format(error))
            }
        )
        Volley.newRequestQueue(getActivity()?.getApplicationContext()).add(jsonObjectRequest)

        var arryAdaptar_hour = getActivity()?.let {ArrayAdapter(it,R.layout._menu,arryList_hour)}
        hour.setAdapter(arryAdaptar_hour)
        hour.setThreshold(1)
    }
    fun AutoList_Min(a:Boolean,b:Int = 0){
        var dk : AutoCompleteTextView = mDialogView.dk
        var arryList_dk: ArrayList<String> = ArrayList()
        if(a){
            arryList_dk.add("00")
            arryList_dk.add("30")
        }
        else if(!a) {
            if (dk_[b] == 1)
                arryList_dk.add("00")
            else if (dk_[b] == 2)
                arryList_dk.add("30")
        }
        var arryAdaptar_arryList_dk = getActivity()?.let {ArrayAdapter(it,R.layout._menu,arryList_dk)}
        dk.setAdapter(arryAdaptar_arryList_dk)
        dk.setThreshold(1)
    }
}