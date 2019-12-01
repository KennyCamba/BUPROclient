package com.espol.i3lab.ideando

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_registro.*

class Registro : AppCompatActivity() {

    val myContext = this

    companion object{
        @SuppressLint("StaticFieldLeak")
        var activity: Activity? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        activity = this@Registro
        continuar.setOnTouchListener {v, event ->
            when(event?.action){
                MotionEvent.ACTION_DOWN -> {
                    v.setBackgroundColor(Color.parseColor("#392FD8"))
                }
                MotionEvent.ACTION_UP -> {
                    v.setBackgroundColor(Color.parseColor("#241BBF"))
                    val api = APIRest(this)
                    val clave = AESUtils.encrypt(pass.text.toString());

                    val user = User(user.text.toString(), clave,
                        name.text.toString(), lastName.text.toString(),
                        email.text.toString(), phone.text.toString(), birthday.text.toString(), "hombre")

                    val gson = Gson()
                    val values = gson.toJson(user)

                    val callback = object : VolleyCallback() {
                        override fun onSuccessResponse(result: String) {
                            val mResponse = gson.fromJson<HashMap<String, Object>>(result, HashMap::class.java)
                            val error = mResponse["error"] as Boolean
                            if(error){
                                Toast.makeText(myContext, "${mResponse["mensaje"]}", Toast.LENGTH_SHORT).show()
                            }else{
                                val intent = Intent(myContext, Confirmar::class.java)
                                startActivity(intent)
                            }
                        }
                        override fun onError(error: String) {
                            Toast.makeText(myContext, error, Toast.LENGTH_LONG).show()
                        }

                    }
                    api.post("http://192.168.0.2:3000/create_user", values, callback)
                }
            }
            true
        }
    }


}
