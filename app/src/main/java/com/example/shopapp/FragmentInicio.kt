package com.example.shopapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.models.SlideModel

import com.example.shopapp.adapters.PeliculaAdapter
import com.example.shopapp.databinding.FragmentInicioBinding
import com.example.shopapp.entities.Banner
import com.example.shopapp.entities.Categorias
import com.example.shopapp.entities.EstructuraRecyclerviewParent
import com.example.shopapp.entities.Productos
import com.example.shopapp.viewmodels.FragmentInicioVM
import com.google.firebase.firestore.*


class FragmentInicio : Fragment() {

    // listas para recylerviewParent
    var estructuraRecyclerviewParent: ArrayList<EstructuraRecyclerviewParent> =arrayListOf()
    private lateinit var peliculasArray: ArrayList<Productos>
    private  var bannerArray: ArrayList<SlideModel> = ArrayList()
     var categorias: ArrayList<Categorias> = arrayListOf()

    private lateinit var myAdapter: PeliculaAdapter
    // bindig
    private val db = FirebaseFirestore.getInstance()
    private var _binding: FragmentInicioBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentInicioBinding.inflate(inflater,container,false)
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        estructuraRecyclerviewParent.add(EstructuraRecyclerviewParent(0,"banner"))
        estructuraRecyclerviewParent.add(EstructuraRecyclerviewParent(2,"cateogorias"))
        estructuraRecyclerviewParent.add(EstructuraRecyclerviewParent(1,"DESTACADO DE LA SEMANA"))
        estructuraRecyclerviewParent.add(EstructuraRecyclerviewParent(1,"LO MAS POPULAR"))
        estructuraRecyclerviewParent.add(EstructuraRecyclerviewParent(1,"MEJOR VALORADOS"))

        // Specify layout for recycler view
        binding.recyclerviewProductosParent.layoutManager = GridLayoutManager(this.context,1, RecyclerView.VERTICAL,false)
        binding.recyclerviewProductosParent.setHasFixedSize(true)

        peliculasArray = arrayListOf()

        myAdapter = PeliculaAdapter(estructuraRecyclerviewParent,peliculasArray,categorias,bannerArray)
        binding.recyclerviewProductosParent.adapter = myAdapter




       /* val imgList = ArrayList<SlideModel>()
        imgList.add(SlideModel("https://somedia.linio.com/mx/img/loyalty/banner-header-loyalty-mobile.jpg"))
        imgList.add(SlideModel("https://image.freepik.com/vector-gratis/banner-musica-electro-verano-fiesta-evento-concierto-forma-colorida-sobre-fondo-negro_85212-70.jpg"))
        imgList.add(SlideModel("https://png.pngtree.com/thumb_back/fh260/background/20201026/pngtree-black-friday-offer-banner-black-background-image_428633.jpg"))
*/



        EventChangeListenerBanner()
        EventChangeListener()
        getAllCategorias()


    }



    private fun EventChangeListener(){

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
                myAdapter.notifyDataSetChanged()


            }


        })

    }
    private fun getAllCategorias(){

        db.collection("categorias").addSnapshotListener(object : EventListener<QuerySnapshot> {

            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null) {

                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED){

                        categorias.add(dc.document.toObject(Categorias::class.java))
                    }

                }
                myAdapter.notifyDataSetChanged()


            }


        })

    }
    private fun EventChangeListenerBanner(){

        db.collection("banner").addSnapshotListener(object : EventListener<QuerySnapshot> {

            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {
                if (error != null) {

                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED){

                        bannerArray.add(SlideModel(dc.document.data["imageUrl"].toString()))

                    }

                }



            }


        })


    }

}