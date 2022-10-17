package com.autG.oncln

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import com.autG.oncln.R

class CadastroOnCln : AppCompatActivity() {


    private lateinit var activity_cadastro_on_cln_spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_on_cln)

        activity_cadastro_on_cln_spinner = findViewById(R.id.sala_spinner)


    }

    fun cadastrarCln(){

    }


}

