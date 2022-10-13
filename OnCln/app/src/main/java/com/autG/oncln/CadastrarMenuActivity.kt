package com.autG.oncln

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.autG.oncln.databinding.ActivityCadastrarMenuBinding
import com.autG.oncln.databinding.ActivityCentralDeAjudaBinding

class CadastrarMenuActivity : AppCompatActivity()  {
    lateinit var binding : ActivityCadastrarMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.include.textTitulo.text = "Cadastrar ou editar"

    }
}