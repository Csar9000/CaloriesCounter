package com.example.caloriescounter.database

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.caloriescounter.Food

@Dao
interface DAO {
    @Query("SELECT * FROM foodTable")
    fun getAllFood(): LiveData<List<EntityFood>>
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(entityFood: EntityFood)
    @Delete
    fun delete(entityFood: EntityFood?)
}