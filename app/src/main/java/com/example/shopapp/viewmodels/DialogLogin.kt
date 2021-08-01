package com.example.shopapp.viewmodels

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.denzcoskun.imageslider.models.SlideModel
import com.example.shopapp.R
import com.example.shopapp.databinding.DialogDetailBinding
import com.example.shopapp.databinding.DialogLoginBinding


class DialogLogin : DialogFragment() {


    private var _binding: DialogLoginBinding? =null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        isCancelable = false
        _binding = DialogLoginBinding.inflate(inflater,container,false)
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


        binding.btnBack.setOnClickListener {

            NavHostFragment.findNavController(this).navigate(R.id.action_dialogLogin_to_fragmentMiCuenta)
        }

        binding.iconGoogle.setOnClickListener{

        }
    }



}