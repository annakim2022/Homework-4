package com.example.homework4


import kotlinx.coroutines.flow.Flow
import java.sql.Date

class DreamRepository(private val dreamDao: DreamDAO) {

    // Room does not return queries on main thread
    // it requires you to run queries on a separate thread

    // store all the results into a list and make it a public property
    // for each of the method in the DAO, you need to write something to execute them in separate threads

    // getAlphabeticalDream
    val allDreams: Flow<List<Dream>> = dreamDao.getAlphabeticalDreams()

    // suspend -> room runs all suspend functions/queries off the main thread
    // so we just call it and embed it in a method that we can use later

    suspend fun insert (dream:Dream) {
        dreamDao.insert(dream)
    }

    suspend fun delete (id:Int){
        dreamDao.delete(id)
    }

    fun select (id:Int): Flow<Dream>{
        return dreamDao.select(id)
    }

    suspend fun update (id:Int, title:String, date:String, content:String, reflection:String, emotion:String){
        dreamDao.update(id, title, date, content, reflection, emotion,)
    }





}