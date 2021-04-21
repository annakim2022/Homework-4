package com.example.homework4

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.security.AccessControlContext

@Database(entities = arrayOf(Dream::class), version = 1, exportSchema = false)
public abstract class DreamRoomDatabase: RoomDatabase() { // has to be abstract
    abstract fun dreamDAO():DreamDAO // getter

    companion object{
        // there should only be one instance of the database for the whole app

        // singleton = software design pattern
        // useful when you need exactly 1 object to coordinate actions across the system
        // use singleton to prevent multiple instances of database opening at the same time
        @Volatile
        private var INSTANCE:DreamRoomDatabase? = null
        // write a method to get the database

        fun getDatabase(context: Context): DreamRoomDatabase {
            // if our instance is not null, return it
            // if it is null, create a database
            return INSTANCE ?: synchronized(this) {
                // create the database
                // return the database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DreamRoomDatabase::class.java,
                    "dream_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}