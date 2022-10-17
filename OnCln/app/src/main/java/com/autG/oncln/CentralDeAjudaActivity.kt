package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.drawable.toDrawable
import com.autG.oncln.databinding.ActivityCentralDeAjudaBinding
import com.autG.oncln.databinding.ActivityMainBinding
import com.autG.oncln.databinding.ActivityTextoBinding

class CentralDeAjudaActivity : AppCompatActivity() {

    lateinit var binding: ActivityCentralDeAjudaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCentralDeAjudaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.includea.textTitulo.text = "Central de ajuda"
        binding.btnSalas.botaoAzul.text = "Suporte 24 h"

        binding.btnEquipamentos.buttonBorder.setCompoundDrawables(getDrawable(R.drawable.ic_equipments),
                null,null,null)

    }


}