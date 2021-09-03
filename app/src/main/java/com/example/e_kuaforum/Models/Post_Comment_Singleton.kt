package com.example.e_kuaforum.Models

import org.json.JSONArray


class Post_Comment_Singleton private constructor(){
    private lateinit var Coments: JSONArray

    companion object {
        @Volatile
        private var INSTANCE: Post_Comment_Singleton? = null
        fun getInstance() =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Post_Comment_Singleton().also {
                    INSTANCE = it
                }
            }
    }
    fun getComents(): JSONArray
    {
        return Coments
    }

    fun setComents(Coments:JSONArray)
    {
        this.Coments=Coments
    }
}