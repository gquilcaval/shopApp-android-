package com.example.shopapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.shopapp.adapters.PeliculaAdapter
import com.example.shopapp.databinding.ActivityMainBinding
import com.example.shopapp.databinding.FragmentInicioBinding
import com.example.shopapp.viewmodels.FragmentInicioVM

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigation = binding.bottomNavigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigation.setupWithNavController(navController)


        var badge = binding.bottomNavigation.getOrCreateBadge(R.id.fragmentCarrito)
        badge.isVisible = true
// An icon only badge will be displayed unless a number is set:
        badge.number = 99
    }
}