package com.example.shopapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel

import com.example.shopapp.R
import com.example.shopapp.databinding.CardviewBannerBinding
import com.example.shopapp.databinding.ParentRecyclerviewCategoriasBinding
import com.example.shopapp.databinding.ParentRecyclerviewItemsBinding
import com.example.shopapp.entities.Categorias
import com.example.shopapp.entities.EstructuraRecyclerviewParent


import com.example.shopapp.entities.Productos





class PeliculaAdapter(private val estructura: List<EstructuraRecyclerviewParent>, val peliculas: List<Productos> =  mutableListOf(),val categorias: List<Categorias> =  mutableListOf(),
                      val bannerArray: List<SlideModel>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private lateinit var myAdapter: ProductoChildAdapter
    private lateinit var adapterCategoria: CategoriaChildAdapter
    companion object {
         const val TYPE_BANNER: Int = 0
         const val TYPE_PRODUCTO: Int = 1
        const val TYPE_CATEGORIA: Int = 2
    }
    //view holder 1
    private inner class  BannerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var binding2 = CardviewBannerBinding.bind(itemView)

        fun  bind(position: Int){
            binding2.sliderBanner.setImageList(bannerArray)

        }
    }
    private inner class  CategoriaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var binding = ParentRecyclerviewCategoriasBinding.bind(itemView)

        fun  bind(position: Int){

            binding.recyclerviewCategoriasChild.layoutManager = GridLayoutManager(itemView.context,1, RecyclerView.HORIZONTAL,false)
            binding.recyclerviewCategoriasChild.setHasFixedSize(true)
            adapterCategoria = CategoriaChildAdapter(categorias)
            binding.recyclerviewCategoriasChild.adapter = adapterCategoria

           /* binding.iconGames.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_fragmentInicio_to_categoriaProductsFragment)
            }*/

        }
    }
    private inner class  ProductorViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       var binding = ParentRecyclerviewItemsBinding.bind(itemView)

        fun  bind(position: Int){

            binding.tvTitulo.text = estructura[position].descripcion


            binding.recyclerviewPeliculasChild.layoutManager = GridLayoutManager(itemView.context,1, RecyclerView.HORIZONTAL,false)
            binding.recyclerviewPeliculasChild.setHasFixedSize(true)
            myAdapter= ProductoChildAdapter(peliculas)
            binding.recyclerviewPeliculasChild.adapter = myAdapter
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_BANNER){
            return BannerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.cardview_banner, parent, false)
            )
        }
        else if (viewType == TYPE_CATEGORIA){
            return CategoriaViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.parent_recyclerview_categorias, parent, false)
            )
        }
        else{
            return ProductorViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.parent_recyclerview_items, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return estructura.size
    }

    override fun getItemViewType(position: Int):Int{
        return estructura[position].type
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (estructura[position].type) {
            TYPE_BANNER -> (holder as BannerViewHolder).bind(position)
            TYPE_CATEGORIA -> (holder as CategoriaViewHolder).bind(position)
            TYPE_PRODUCTO -> (holder as ProductorViewHolder).bind(position)

        }




    }







}