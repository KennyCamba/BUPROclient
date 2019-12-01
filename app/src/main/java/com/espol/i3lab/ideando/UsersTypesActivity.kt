package com.espol.i3lab.ideando

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_users_types.*

class UsersTypesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_types)
        SplashActivity.activity?.finish()
        val array = arrayOf(hunter, emprendedor, inversionista, cliente)

        for(btn in array){
            btn.setOnTouchListener{v, event ->
                when(event?.action){
                    MotionEvent.ACTION_DOWN -> {
                        v.setBackgroundColor(Color.parseColor("#392FD8"))
                    }
                    MotionEvent.ACTION_UP -> {
                        v.setBackgroundColor(Color.parseColor("#241BBF"))
                        if(v is Button){
                            Toast.makeText(this, v.text, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                true

            }
        }
    }
}
