package com.example.caloriescounter.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "foodTable")
data class EntityFood(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title")
    var title: String?,
    @ColumnInfo(name = "calories")
    var calories: String?,
    @ColumnInfo(name = "fat")
    var fat:String?,
    @ColumnInfo(name = "carbons")
    var carbons:String?,
    @ColumnInfo(name = "protein")
    var protein:String?,
)