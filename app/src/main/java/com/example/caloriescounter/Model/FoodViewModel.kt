package com.example.caloriescounter.Model

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import android.app.Application
import com.example.caloriescounter.Food
import com.example.caloriescounter.database.DataBase
import com.example.caloriescounter.database.EntityFood


public class FoodViewModel(application:Application): AndroidViewModel(application){
    private val db: DataBase? = DataBase.getInstance(application)
    internal val allFood : LiveData<List<EntityFood>> = (db?.DAO()?.getAllFood() ?: DataBase) as LiveData<List<EntityFood>>

    fun insert(food: EntityFood){
        db?.DAO()?.insert(food)
    }
    fun delete(food: EntityFood){
        db?.DAO()?.delete(food)
    }
}