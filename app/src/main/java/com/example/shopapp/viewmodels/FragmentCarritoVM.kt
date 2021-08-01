package com.example.shopapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.shopapp.db.AppDatabase
import com.example.shopapp.entities.Carrito
import kotlinx.coroutines.launch

class FragmentCarritoVM(application: Application): AndroidViewModel(application) {

    private val db: AppDatabase = AppDatabase.getInstance(getApplication())


    internal val notas : LiveData<List<Carrito>> = db.carritoDao.getAll()

    fun getAll(): LiveData<List<Carrito>> {
        return notas
    }







    fun insert(nota: Carrito){

        viewModelScope.launch {
            db.carritoDao.insertAll(nota)
        }

    }
    fun update(nota: Carrito){

        viewModelScope.launch {
            db.carritoDao.update(nota)
        }

    }
    fun delete(id: String){

        viewModelScope.launch {
            db.carritoDao.delete(id)
        }
    }

}