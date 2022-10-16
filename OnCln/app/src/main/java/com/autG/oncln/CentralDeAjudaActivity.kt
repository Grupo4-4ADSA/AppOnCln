package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.autG.oncln.databinding.ActivityCentralDeAjudaBinding
import com.autG.oncln.databinding.ActivityMainBinding

class CentralDeAjudaActivity : AppCompatActivity() {

    lateinit var binding : ActivityCentralDeAjudaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCentralDeAjudaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.includea.textTitulo.text = "Central de ajuda"

    }


}