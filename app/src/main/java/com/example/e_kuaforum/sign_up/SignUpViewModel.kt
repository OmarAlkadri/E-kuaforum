package com.example.e_kuaforum.sign_up

import androidx.lifecycle.ViewModel
import java.util.regex.Matcher
import java.util.regex.Pattern

class SignUpViewModel: ViewModel() {

    fun Check_Maill(name:String):Boolean{
        val pattern: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\.\\_]{1,256}" +
                    "\\@" +
                    "[a-z]{0,64}" +
                    "\\." +
                    "[a-z]{0,25}"
        )
        val matcher: Matcher = pattern.matcher(name)
        return matcher.matches()
    }

    fun Check_Password(Password:String):Boolean{
        val pattern: Pattern = Pattern.compile(
            "[a-zA-Z]{1,5}"+
                    "[0-9]{1,3}"+
                    "[a-zA-Z0-9]{1,50}"
        )
        val matcher: Matcher = pattern.matcher(Password)
        return matcher.matches()
    }

    fun Check_Phone(Phone:String):Boolean{
        val pattern: Pattern = Pattern.compile(
            "[1-9]{3}" +
                    "[0-9]{7}"
        )
        val matcher: Matcher = pattern.matcher(Phone)
        return matcher.matches()
    }




}