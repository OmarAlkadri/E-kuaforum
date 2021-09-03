package com.example.e_kuaforum.barbershop_show_page

class barberShop{
    private var Adres:String
    private var BarberShowName:String
    private var WorkingHours:String
    private var BarberImg:String

    constructor(BarberShowName:String,Adres:String,WorkingHours:String,BarberImg:String) {
        this.BarberShowName = BarberShowName
        this.Adres = Adres
        this.WorkingHours = WorkingHours
        this.BarberImg = BarberImg
    }

    fun getBarberShowName():String{
        return BarberShowName
    }
    fun getAdres():String{
        return Adres
    }
    fun getWorkingHours():String{
        return WorkingHours
    }
    fun getBarberImg():String{
        return BarberImg
    }
}