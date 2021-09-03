package com.example.e_kuaforum.Models

class Barber_Singleton private constructor(){
    private var name = ArrayList<String>()
    companion object {
        @Volatile
        private var INSTANCE: Barber_Singleton? = null
        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Barber_Singleton().also {
                    INSTANCE = it
                }
            }
    }
    fun getBarberName():ArrayList<String>
    {
        return name
    }

    fun setBarberName(name:String)
    {
        this.name.add(name)
    }
}