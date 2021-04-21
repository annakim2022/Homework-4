package com.example.homework4


import android.app.Application

class DreamApplication:Application() {
    // create 1 instance of database
    // 1 instance of repository

    // lazy -> the value gets computed or executed only upon first access
    val database by lazy{DreamRoomDatabase.getDatabase(this)}
    val repository by lazy {DreamRepository(database.dreamDAO())}

}