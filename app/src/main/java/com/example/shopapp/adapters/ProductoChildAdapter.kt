package com.example.shopapp.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.shopapp.R
import com.example.shopapp.databinding.CardviewProductoBinding
import com.example.shopapp.entities.Productos


class ProductoChildAdapter(private val peliculas: List<Productos>)
    : RecyclerView.Adapter<ProductoChildAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_producto,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        Glide.with(holder.itemView.context).load(peliculas[position].foto_1.toString()).into(holder.foto);
        holder.nombre.text = peliculas[position].nombre
        holder.precio.text = "S/ " +peliculas[position].precio.toString()
        //Picasso.get().load(peliculas[position].foto.toString()).into(holder.foto);
        holder.cardview.setOnClickListener{

           val bundle = Bundle()
            bundle.putString("nombre", peliculas[position].nombre.toString())
            bundle.putInt("precio", peliculas[position].precio)
            bundle.putString("foto_1", peliculas[position].foto_1.toString())
            bundle.putString("foto_2", peliculas[position].foto_2.toString())
            bundle.putString("caracteristicas", peliculas[position].caracteristicas.toString())
            Navigation.findNavController(it).navigate(R.id.action_fragmentInicio_to_dialogDetail, bundle)


        }




    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var binding = CardviewProductoBinding.bind(itemView)


        val foto = binding.imgFoto
        val cardview = binding.cardviewPelicula
        val nombre = binding.tvNombre
        val precio = binding.tvPrecio


    }


}