package com.example.shopapp

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shopapp.databinding.DialogDetailBinding

import com.example.shopapp.entities.Carrito
import com.example.shopapp.viewmodels.FragmentCarritoVM
import com.google.android.material.snackbar.Snackbar
import org.jetbrains.anko.doAsync

class DialogDetail : DialogFragment() {

    // Get the view model
    private val model: FragmentCarritoVM by viewModels()

    private var fotos = ArrayList<SlideModel>()
    private var _binding: DialogDetailBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        isCancelable = false
        _binding = DialogDetailBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun getTheme(): Int {
        return R.style.DialogTheme
    }
    /** The system calls this only when creating the layout in a dialog. */
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var nombre = arguments?.getString("nombre")
        var precio = (arguments?.getInt("precio"))

        var caracteristicas = arguments?.getString("caracteristicas")
        var foto_1 = arguments?.getString("foto_1")
        var foto_2 = arguments?.getString("foto_2")

        binding.tvTitulo.text = nombre
        binding.tvPrecio.text =  precio.toString()
        binding.tvCaracteristicas.text =  caracteristicas
        binding.tvCantidad.text = binding.tvCantidad.text.toString()
        fotos.add(SlideModel(foto_1))
        fotos.add(SlideModel(foto_2))
        binding.sliderBanner.setImageList(fotos)


        binding.iconComprar.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_dialogDetail_to_fragmentCarrito)
        }
        binding.btnBack.setOnClickListener {

            NavHostFragment.findNavController(this).navigate(R.id.action_dialogDetail_to_fragmentInicio)
        }
        binding.iconAgregar.setOnClickListener {

            doAsync {
                model.insert(Carrito(0,nombre.toString(),precio.toString().toInt(),binding.tvCantidad.text.toString().toInt() ,foto_1.toString()))

            }

            Snackbar.make(it, "Agregado a tu carrito", Snackbar.LENGTH_LONG).setAnchorView(binding.iconComprar).setAction("Cancelar"){}.show()
        }
        binding.iconSumar.setOnClickListener {

            binding.tvCantidad.text = (binding.tvCantidad.text.toString().toInt() + 1).toString()
        }
        binding.iconRestar.setOnClickListener {
            if (binding.tvCantidad.text != "0"){
                binding.tvCantidad.text = (binding.tvCantidad.text.toString().toInt() - 1).toString()
            }

        }
    }



}