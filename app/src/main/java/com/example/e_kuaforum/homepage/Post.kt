package com.example.e_kuaforum.homepage

import org.json.JSONArray
import org.json.JSONObject

class Post{
    private var PostId:Int
    private var PostText:String
    private var PostTime:String
    private var Post_Imag_Video:String
    private var Barber:String
    private var Comment: JSONArray
    private var Like:Int

    constructor(PostId:Int,PostText:String,PostTime:String,Post_Imag_Video:String,Barber:String,Comment:JSONArray,Like:Int) {
        this.PostId = PostId
        this.PostText = PostText
        this.PostTime = PostTime
        this.Post_Imag_Video = Post_Imag_Video
        this.Barber = Barber
        this.Comment = Comment
        this.Like = Like
    }
    fun getPostId():Int{
        return PostId
    }
    fun setPostId(Id:Int){
         this.PostId = Id
    }    fun getPostText():String{
        return PostText
    }
    fun getPostTime():String{
        return PostTime
    }
    fun getPostImag_Video():String{
        return Post_Imag_Video
    }
    fun getBarber():String{
        return Barber
    }
    fun getComment():JSONArray{
        return Comment
    }
    fun getLike():Int{
        return Like
    }
}