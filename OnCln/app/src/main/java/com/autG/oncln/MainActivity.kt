package com.autG.oncln

import LoginResponse
import android.content.SharedPreferences
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
import com.autG.oncln.services.Cache
import com.autG.oncln.services.NavigationHost
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity(), NavigationHost, Cache {

    private lateinit var binding: ActivityMainBinding
    private var backScreen: MenuData = MenuData(R.layout.activity_home, R.layout.activity_home)
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

      //aqui
        prefs = getSharedPreferences("preferences", MODE_PRIVATE)

        val cacheLogin = prefs.getBoolean("user", false)

        if (savedInstanceState == null) {
            if (!cacheLogin) {

                supportFragmentManager
                    .beginTransaction()
                    .add(
                        R.id.nav_host_fragment_container,
                        LoginActivity(),
                        LoginActivity().toString()
                    )
                    .add(
                        R.id.nav_fragment_container,
                        setLayoutMenu(R.id.layout_home, R.layout.activity_home),
                        "navbarBottom"
                    )
                    .commit()
                binding.btnMenuLateral.visibility = View.GONE
                binding.navFragmentContainer.visibility = View.GONE
            } else {
                navigateTo(HomeActivity(), false, R.layout.activity_home)
            }
        }

        binding.btnMenuLateral.setOnClickListener {

            binding.navMenuLateral.visibility = View.VISIBLE
            binding.btnMenuLateral.visibility = View.GONE

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.nav_menu_lateral, setLayoutMenuLeft(backScreen.setPage,backScreen.setBackLayout), "menuLateral")
                .commit()
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

        Timer().schedule(150) {
            binding.navFragmentContainer.visibility = View.VISIBLE
            binding.btnMenuLateral.visibility = View.VISIBLE
        }
        val edit = prefs.edit()
        edit.putBoolean("user",true)
        edit.apply()

        menuAction()
    }

    override fun menuAction() {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_menu_lateral, EmptyScreen(), "menuLateral")
            .commit()
            binding.btnMenuLateral.visibility = View.VISIBLE
    }

    override fun insertData(string: String, any: Any) {

        prefs = getSharedPreferences("preferences", MODE_PRIVATE)

        val userData: LoginResponse = any as LoginResponse

        val edit = prefs.edit()
        edit.putString ("userName",userData.nome)
        edit.putInt ("userId",userData.idGestor)
        edit.putInt ("idPredio",userData.idPredio)
        edit.apply()

    }

}
