package com.example.shopapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.shopapp.databinding.FragmentInicioBinding
import com.example.shopapp.databinding.FragmentMicuentaBinding


class FragmentMiCuenta : Fragment() {

    private var _binding: FragmentMicuentaBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMicuentaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.cardIdentificate.setOnClickListener{

            Navigation.findNavController(it).navigate(R.id.action_fragmentMiCuenta_to_dialogLogin)
        }
    }


}