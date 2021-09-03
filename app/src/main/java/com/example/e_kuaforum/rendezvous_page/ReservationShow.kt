package com.example.e_kuaforum.rendezvous_page

class ReservationShow {
    private var Adres:String
    private var BarberShowName:String
    private var BarberImg:String
    private var reservationTime:String
    private var paid:Int

    constructor(BarberShowName:String,Adres:String,BarberImg:String,reservationTime:String,paid:Int) {
        this.BarberShowName = BarberShowName
        this.Adres = Adres
        this.BarberImg = BarberImg
        this.reservationTime = reservationTime
        this.paid = paid
    }

    fun getBarberShowName():String{
        return BarberShowName
    }
    fun getAdres():String{
        return Adres
    }
    fun getReservationTime():String{
        return reservationTime
    }
    fun getPaid():String{
        return Adres
    }
    fun getBarberImg():String{
        return BarberImg
    }
}