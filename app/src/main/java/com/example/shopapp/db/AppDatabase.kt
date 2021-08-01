package com.example.shopapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopapp.dao.CarritoDao
import com.example.shopapp.entities.Carrito

@Database(entities = arrayOf(Carrito::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val carritoDao: CarritoDao
    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null
        fun getInstance(context: Context):AppDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "shopDB"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}