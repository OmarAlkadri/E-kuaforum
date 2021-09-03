package com.example.e_kuaforum.homepage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_kuaforum.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val c = findViewById<BottomNavigationView>(R.id.bottomNavig)
        val b = findNavController(R.id.fragmentContainerView)
        c.setupWithNavController(b)
    }
}