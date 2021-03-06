package com.example.homework4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.util.*

// data class Task (val task:String)

// @Entity -> class that represents a SQLite table
@Entity(tableName = "dream_table")
class Dream(@PrimaryKey (autoGenerate = true) val id:Int,
            @ColumnInfo(name="title") val title:String,
            @ColumnInfo(name="date") val date:String,
            @ColumnInfo(name="content") val content:String,
            @ColumnInfo(name="reflection") val reflection:String,
            @ColumnInfo(name="emotion") val emotion:String)