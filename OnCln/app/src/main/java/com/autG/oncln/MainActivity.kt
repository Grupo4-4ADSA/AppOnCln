package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.autG.oncln.databinding.ActivityMainBinding
import com.autG.oncln.databinding.NavbarBottomBinding
import com.autG.oncln.menus.NavBarBottom
import com.autG.oncln.services.NavigationHost

class MainActivity : AppCompatActivity(), NavigationHost {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment_container, CentralDeAjudaActivity(), CentralDeAjudaActivity().toString())
                .add(R.id.nav_fragment_container, NavBarBottom(), null)
                .commit()
        }
    }

    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean) {
        val transaction: FragmentTransaction = supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.nav_host_fragment_container, fragment)

        if (addToBackStack){
            transaction.addToBackStack(fragment.toString())
        }
        transaction.commit()
    }
}