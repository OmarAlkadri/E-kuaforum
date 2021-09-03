package com.example.e_kuaforum.Models

import org.json.JSONArray


class User_Singleton private constructor(){
    private var UserId: Int = 0
    private var PersonelId: Int = 0
    private var age: Int = 0
    private lateinit var userName: String
    companion object {
        @Volatile
        private var INSTANCE: User_Singleton? = null
        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: User_Singleton().also {
                    INSTANCE = it
                }
            }
    }
    fun getUserId():Int
    {
        return UserId
    }
    fun setUserId(UserId:Int)
    {
        this.UserId=UserId
    }
    fun getPersonelId():Int
    {
        return PersonelId
    }
    fun setPersonelId(UserId:Int)
    {
        this.PersonelId=UserId
    }


    fun getage():Int
    {
        return UserId
    }
    fun setage(age:Int)
    {
        this.age=age
    }
    fun getuserName():String
    {
        return userName
    }
    fun setuserName(UserId:String)
    {
        this.userName=UserId
    }
}