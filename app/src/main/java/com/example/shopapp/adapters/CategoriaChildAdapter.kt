package com.example.peliculasapp.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.shopapp.R
import com.example.shopapp.databinding.CardviewCategoriaBinding
import com.example.shopapp.entities.Categorias


class CategoriaChildAdapter(private val categorias: List<Categorias>)
    : RecyclerView.Adapter<CategoriaChildAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_categoria,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {



        holder.nombre.text = categorias[position].nombre

        Glide.with(holder.itemView.context).load(categorias[position].iconUrl.toString()).into(holder.icono);

        //Picasso.get().load(peliculas[position].foto.toString()).into(holder.foto);
        holder.cardview.setOnClickListener{

           val bundle = Bundle()
            bundle.putString("nombre", categorias[position].nombre.toString())

            Navigation.findNavController(it).navigate(R.id.action_fragmentInicio_to_categoriaProductsFragment,bundle)


        }




    }

    override fun getItemCount(): Int {
        return categorias.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var binding = CardviewCategoriaBinding.bind(itemView)


        val cardview = binding.cardviewCategoria
        val nombre = binding.tvNombre
        val icono = binding.icon



    }


}