package com.example.shopapp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopapp.entities.Productos
import com.google.firebase.firestore.*
import org.jetbrains.anko.doAsync
import javax.inject.Inject
import javax.inject.Singleton


class ProductRepository  {
    /*
    private val productos = MutableLiveData<List<Productos>>()
    private val db = FirebaseFirestore.getInstance()

     fun getProductListFromFirestore(): LiveData<List<Productos>> {
    doAsync {
        var peliculasArray:ArrayList<Productos> = arrayListOf()
        db.collection("productos").addSnapshotListener(object : EventListener<QuerySnapshot> {

            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null) {

                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED){

                        peliculasArray.add(dc.document.toObject(Productos::class.java))
                    }

                }
                productos.value = peliculasArray



            }


        })


    }
        return  productos
    }*/
}