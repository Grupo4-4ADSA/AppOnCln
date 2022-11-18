package com.autG.oncln

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.autG.oncln.dataClass.MenuData
import com.autG.oncln.databinding.ActivityMainBinding
import com.autG.oncln.menus.EmptyScreen
import com.autG.oncln.menus.NavBarBottom
import com.autG.oncln.menus.NavigationBar
import com.autG.oncln.services.NavigationHost


class MainActivity : AppCompatActivity(), NavigationHost {

    private lateinit var binding: ActivityMainBinding
    private lateinit var backScreen: MenuData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {

            supportFragmentManager
                .beginTransaction()
                .add(R.id.nav_host_fragment_container, LoginActivity(), LoginActivity().toString())
                .add(
                    R.id.nav_fragment_container,
                    setLayoutMenu(R.id.layout_home, R.layout.activity_home),
                    "navbarBottom"
                )
                .commit()
        }

        binding.btnMenuLateral.setOnClickListener {

            binding.navMenuLateral.visibility = View.VISIBLE
            binding.btnMenuLateral.visibility = View.GONE

            if (savedInstanceState == null) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_menu_lateral, setLayoutMenuLeft(backScreen.setPage,backScreen.setBackLayout), "menuLateral")
                    .commit()
            }
        }
    }

    fun setLayoutMenu(fragment: Int, back: Int?): NavBarBottom {

        val args = Bundle()
        val data = MenuData(fragment, back)
        args.putSerializable("screen", data)

        val navBarBottom = NavBarBottom.newIntance()
        navBarBottom.arguments = args

        return navBarBottom
    }

    fun setLayoutMenuLeft(fragment: Int, back: Int?): NavigationBar {

        val args = Bundle()
        val data = MenuData(fragment, back)
        args.putSerializable("screen", data)

        val navigationBar = NavigationBar.newIntance()
        navigationBar.arguments = args

        return navigationBar
    }

    override fun navigateTo(fragment: Fragment, addToBackStack: Boolean, layout: Int) {

        backScreen = MenuData(layout, layout)

        val transaction: FragmentTransaction = supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment_container, fragment)
            .replace(
                R.id.nav_fragment_container,
                setLayoutMenu(layout, backScreen.setBackLayout),
                "screenValue"
            )

        if (addToBackStack) {
            transaction.addToBackStack(fragment.toString())
        }
        transaction.commit()

        binding.navFragmentContainer.visibility = View.VISIBLE
        binding.btnMenuLateral.visibility = View.VISIBLE

        menuAction()
    }

    override fun menuAction() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_menu_lateral, EmptyScreen(), "menuLateral")
            .commit()
            binding.btnMenuLateral.visibility = View.VISIBLE
    }
}