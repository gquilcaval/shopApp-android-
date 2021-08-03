package com.example.shopapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.shopapp.entities.Carrito

@Dao
interface CarritoDao {


        @Query("SELECT * FROM carrito")
        fun getAll(): LiveData<List<Carrito>>


        @Insert
        suspend fun insertAll(vararg carrito: Carrito)

        @Update
        suspend fun update(carrito: Carrito)


        @Query("DELETE FROM carrito WHERE uid=:id")
        suspend fun delete(id: String)

}