package com.example.shopapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Carrito(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "producto") val producto: String,
    @ColumnInfo(name = "precio") val precio: Int,
    @ColumnInfo(name = "cantidad") var cantidad: Int,
    @ColumnInfo(name = "foto") val foto: String,


    )