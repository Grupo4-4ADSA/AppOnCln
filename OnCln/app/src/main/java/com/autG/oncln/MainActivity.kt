package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.autG.oncln.menus.NavBarBottom
import com.autG.oncln.services.NavigationHost

class MainActivity : AppCompatActivity(), NavigationHost {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment_container, LoginActivity(), LoginActivity().toString())
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
        findViewById<View>(R.id.nav_fragment_container).visibility = View.VISIBLE
    }
}