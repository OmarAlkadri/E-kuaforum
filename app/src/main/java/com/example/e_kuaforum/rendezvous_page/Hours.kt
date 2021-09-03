package com.example.e_kuaforum.rendezvous_page

class Hours {
    private var OpeningTime:Int
    private var ClosingTime:Int
    constructor(OpeningTime:Int,ClosingTime:Int) {
        this.OpeningTime = OpeningTime
        this.ClosingTime = ClosingTime
    }
    fun getOpeningTime():Int{
        return OpeningTime
    }
    fun getclosingTime():Int{
        return ClosingTime
    }
}
