package com.example.e_kuaforum.rendezvous_page

class ReservationShow {
    private var Adres:String
    private var BarberShowName:String
    private var BarberImg:String
    private lateinit var Day:String
    private var Hour:String
    private var Min:String
    private var paid:Int

    constructor(BarberShowName:String,Adres:String,BarberImg:String,Day:Int,Hour:String,Min:String,paid:Int) {
        this.BarberShowName = BarberShowName
        this.Adres = Adres
        this.BarberImg = BarberImg

        if(Day==1){
            this.Day = "Monday"
        }
        else if(Day==2)
        {
            this.Day = "Tuesday"
        }
        else if(Day==3)
        {
            this.Day = "Wednesday"
        }
        else if(Day==4)
        {
            this.Day = "Thursday"
        }
        else if(Day==5)
        {
            this.Day = "Friday"
        }
        else if(Day==6)
        {
            this.Day = "Saturday"
        }


        this.Hour = Hour
        this.Min = Min
        this.paid = paid
    }

    fun getBarberShowName():String{
        return BarberShowName
    }
    fun getAdres():String{
        return Adres
    }
    fun getReservationTime():String{
        return "$Day - $Hour:$Min"
    }
    fun getPaid():String{
        return Adres
    }
    fun getBarberImg():String{
        return BarberImg
    }
}