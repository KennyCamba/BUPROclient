package com.espol.i3lab.ideando

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Confirmar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmar)
        Registro.activity?.finish()

    }

}
