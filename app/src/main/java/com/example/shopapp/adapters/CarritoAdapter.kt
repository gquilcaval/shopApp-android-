package com.example.shopapp.adapters

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopapp.R
import com.example.shopapp.databinding.CardviewCarritoBinding
import com.example.shopapp.entities.Carrito
import com.example.shopapp.viewmodels.FragmentCarritoVM
import org.jetbrains.anko.internals.AnkoInternals.createAnkoContext

class CarritoAdapter(private val carrito: List<Carrito>, val model: FragmentCarritoVM)
    : RecyclerView.Adapter<CarritoAdapter.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : ViewHolder {
            val v: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.cardview_carrito,parent,false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            Glide.with(holder.itemView.context).load(carrito[position].foto).into(holder.foto);
            holder.producto.text = carrito[position].producto.toString()
            holder.precio.text = carrito[position].precio.toString()
            holder.cantidad.text = carrito[position].cantidad.toString()


            holder.icon_eliminar.setOnClickListener {

                model.delete(carrito[position].uid.toString())
            }

            holder.icon_agregar.setOnClickListener {
                holder.cantidad.text = (holder.cantidad.text.toString().toInt() + 1).toString()

                model.update(Carrito(carrito[position].uid,carrito[position].producto,carrito[position].precio,holder.cantidad.text.toString().toInt(),carrito[position].foto))
            }
            holder.icon_quitar.setOnClickListener {
                if (holder.cantidad.text != "0"){
                    holder.cantidad.text = (holder.cantidad.text.toString().toInt() - 1).toString()
                    model.update(Carrito(carrito[position].uid,carrito[position].producto,carrito[position].precio,holder.cantidad.text.toString().toInt(),carrito[position].foto))
                }
            }
            /*holder.containerNote.setOnClickListener{

                val intent = Intent(holder.itemView.context, RegisterNoteActivity::class.java)
                intent.putExtra("id",  notas[position].uid.toString())
                intent.putExtra("descripcion",  notas[position].descripcion.toString())
                intent.putExtra("titulo",  notas[position].title.toString())
                intent.putExtra("color",  notas[position].color)
                intent.putExtra("idColor",  notas[position].idColor.toString())
                startActivity(holder.itemView.context,intent,null)
            }*/




        }

        override fun getItemCount(): Int {
            return carrito.size
        }

        override fun getItemId(position: Int): Long {
            return super.getItemId(position)
        }

        override fun getItemViewType(position: Int): Int {
            return super.getItemViewType(position)
        }

        class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
            var binding = CardviewCarritoBinding.bind(itemView)
            val producto = binding.tvNombre
            val precio = binding.tvPrecio
            val cantidad = binding.tvCantidad
            val foto = binding.foto
            val icon_eliminar = binding.iconDelete
            val icon_agregar = binding.iconAgregar
            val icon_quitar = binding.iconQuitar



        }


    }