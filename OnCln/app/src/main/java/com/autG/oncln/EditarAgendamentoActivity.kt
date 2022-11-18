package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityCentralDeAjudaBinding

class EditarAgendamentoActivity: Fragment() {
    private lateinit var binding: ActivityCentralDeAjudaBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityCentralDeAjudaBinding.inflate(inflater,container,false)
        return binding.root
    }
}