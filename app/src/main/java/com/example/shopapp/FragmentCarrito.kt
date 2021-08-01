package com.example.shopapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shopapp.adapters.CarritoAdapter
import com.example.shopapp.databinding.FragmentCarritoBinding
import com.example.shopapp.databinding.FragmentInicioBinding
import com.example.shopapp.entities.Carrito
import com.example.shopapp.viewmodels.FragmentCarritoVM


class FragmentCarrito : Fragment() {


    private var _binding: FragmentCarritoBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentCarritoBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Specify layout for recycler view
        binding.recyclerviewCarrito.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL,false)
        binding.recyclerviewCarrito.setHasFixedSize(true)

        // Get the view model
        val model: FragmentCarritoVM by viewModels()
        model.getAll().observe(viewLifecycleOwner,  {

            var tot =0
            it.forEach { carrito -> tot += carrito.precio * carrito.cantidad }

            binding.recyclerviewCarrito.adapter = CarritoAdapter(it,model)


            binding.tvPrecioTotal.text = "S/ "+tot.toString()
        })



    }
}