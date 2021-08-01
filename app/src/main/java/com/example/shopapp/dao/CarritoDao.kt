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

        /*  @Query("SELECT * FROM user WHERE uid IN (:userIds)")
          fun loadAllByIds(userIds: IntArray): List<User>*/

        /*@Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1")
        fun findByName(first: String, last: String): User*/

        @Insert
        suspend fun insertAll(vararg nota: Carrito)

        @Update
        suspend fun update(nota: Carrito)


        @Query("DELETE FROM carrito WHERE uid=:id")
        suspend fun delete(id: String)

}