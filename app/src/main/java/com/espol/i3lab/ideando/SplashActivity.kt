package com.espol.i3lab.ideando

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    companion object{
        @SuppressLint("StaticFieldLeak")
        var activity: Activity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        var session = Session.getInstance().getActiveSession(this)
        if(session != null){
            val intent = Intent(this, UsersTypesActivity::class.java)
            startActivity(intent)
        }

        session = Session.getInstance()
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        entrar.setOnTouchListener {v, event ->
            when(event?.action){
                MotionEvent.ACTION_DOWN -> {
                    v.setBackgroundColor(Color.parseColor("#392FD8"))
                }
                MotionEvent.ACTION_UP -> {
                    v.setBackgroundColor(Color.parseColor("#241BBF"))
                    val callback = object : Token {
                        override fun success(login: Boolean) {
                            if(login){
                                val intent = Intent(this@SplashActivity, UsersTypesActivity::class.java)
                                startActivity(intent)
                            }else{
                                Toast.makeText(this@SplashActivity, "Error al iniciar sesison", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun error(error: String) {
                            Toast.makeText(this@SplashActivity, error, Toast.LENGTH_LONG).show()
                        }

                    }
                    session.login(this@SplashActivity, user.text.toString(), pass.text.toString(), callback)
                    val intent = Intent(this@SplashActivity, UsersTypesActivity::class.java)
                    startActivity(intent)

                }
            }
            true
        }


        registro.setOnTouchListener { v, event ->
            when(event.action){
                MotionEvent.ACTION_UP -> {
                    registro.setBackgroundColor(Color.TRANSPARENT)
                    val intent = Intent(this, Registro::class.java)
                    startActivity(intent)
                }
                MotionEvent.ACTION_DOWN -> {
                    registro.setBackgroundColor(Color.parseColor("#3F3BE7"))
                }
            }
            true
        }

    }
}
