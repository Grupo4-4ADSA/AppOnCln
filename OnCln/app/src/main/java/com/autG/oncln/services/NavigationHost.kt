package com.autG.oncln.services

import androidx.fragment.app.Fragment


interface NavigationHost {
    fun navigateTo(fragment: Fragment, addToBackStack: Boolean)
}