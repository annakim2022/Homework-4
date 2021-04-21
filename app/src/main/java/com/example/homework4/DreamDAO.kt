package com.example.homework4

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

// DAO class should always be either interface OR abstract class
@Dao
interface DreamDAO {

    @Query("SELECT*FROM dream_table ORDER BY id ASC")
    fun getAlphabeticalDreams(): Flow<List<Dream>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dream:Dream)

    @Query("DELETE FROM dream_table WHERE id=:id")
    suspend fun delete(id:Int)

    @Query ("SELECT * FROM dream_table WHERE id=:id")
    fun select(id:Int) : Flow<Dream>

    @Query("UPDATE dream_table SET title=:title, content=:content, reflection=:reflection, emotion=:emotion WHERE id=:id")
    suspend fun update(id:Int, title:String, content:String, reflection:String, emotion:String)

}