package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.autG.oncln.databinding.ActivityAgendamentosBinding
import com.autG.oncln.databinding.ActivityCentralDeAjudaBinding

class AgendamentosActivity : Fragment() {
    private lateinit var binding: ActivityAgendamentosBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityAgendamentosBinding.inflate(inflater,container,false)
        return binding.root
    }
}