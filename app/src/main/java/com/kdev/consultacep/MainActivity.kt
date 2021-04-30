package com.kdev.consultacep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<NavigationView>(R.id.nav_view).setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.btn_grid_layout  -> {
                    Toast.makeText(this, "Gridiii", Toast.LENGTH_SHORT).show()
                    //findNavController(R.id.nav_host_fragment).navigate()
                }

                R.id.menu_cancel_nav_view -> {
                    Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Toast.makeText(this, "Else", Toast.LENGTH_SHORT).show()
                }
            }

            true
        }
    }
}