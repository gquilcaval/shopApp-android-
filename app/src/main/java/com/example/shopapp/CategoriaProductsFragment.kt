package com.example.shopapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.shopapp.adapters.ProductoChildAdapter
import com.example.shopapp.databinding.FragmentCategoriaProductsBinding
import com.example.shopapp.entities.Productos
import com.google.firebase.firestore.*


class CategoriaProductsFragment : Fragment() {
    var productos: ArrayList<Productos> = arrayListOf()
    private lateinit var myAdapter: ProductoChildAdapter

    var nombreCategoria:String? = ""

    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentCategoriaProductsBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoriaProductsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        nombreCategoria = arguments?.getString("nombre")
        binding.tvNombreCategoria.text = nombreCategoria


        // Specify layout for recycler view
        binding.recyclerviewProductos.layoutManager = GridLayoutManager(this.context,2, RecyclerView.VERTICAL,false)
        binding.recyclerviewProductos.setHasFixedSize(true)



        myAdapter = ProductoChildAdapter(productos)
        binding.recyclerviewProductos.adapter = myAdapter

        binding.btnBack.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_categoriaProductsFragment_to_fragmentInicio)
        }
        EventChangeListener()
    }

    private fun EventChangeListener(){

        db.collection("productos").whereEqualTo("categoria", nombreCategoria).addSnapshotListener(object : EventListener<QuerySnapshot> {

            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null) {

                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED){

                        productos.add(dc.document.toObject(Productos::class.java))
                    }

                }
                myAdapter.notifyDataSetChanged()


            }


        })

    }

}