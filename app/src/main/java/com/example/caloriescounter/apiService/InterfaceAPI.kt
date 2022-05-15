package com.example.caloriescounter.apiService

import com.example.caloriescounter.Food
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call


interface InterfaceAPI {


    @GET("findByNutrients/?apiKey=8248a479d8a94ad9af8e241b2dd2169f&minCarbs=0&minFat=0&minProtein=0&random=true")
    fun getFoodList(
        @Query("number") number: Number,
//        @Query("calories") calories: Int,
//        @Query("fat") fat: String,
//        @Query("proteins") proteins: String,
//        @Query("carbs") carbs: String,
    ): Call<List<Food>>

    @GET("https://api.spoonacular.com/recipes/complexSearch/?apiKey=8248a479d8a94ad9af8e241b2dd2169f")
    fun getFoodByTitle(
        @Query("query") query: String,
//        @Query("calories") calories: Int,
//        @Query("fat") fat: String,
//        @Query("proteins") proteins: String,
//        @Query("carbs") carbs: String,
    ): Call<List<Food>>


    companion object Factory {
        fun create(): InterfaceAPI {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.spoonacular.com/recipes/")
                .build()

            return retrofit.create(InterfaceAPI::class.java);
        }
    }
}
