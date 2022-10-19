package com.autG.oncln.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    // Emulador
    //val baseURL = "http://10.0.2.2:3000/"

    // Celular
    //Todo Colocar o ipv4 especifico da sua máquina (cmd + ipconfig)
     val baseURL = "http://192.168.15.10:8002/"

    fun getInstance(): Retrofit {
        return Retrofit
            .Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(baseURL).build()
    }

}