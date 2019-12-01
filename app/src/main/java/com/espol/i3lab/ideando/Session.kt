package com.espol.i3lab.ideando

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.gson.Gson

class Session private constructor() {
    private var token: String? = null
    companion object{
        private var instance: Session = Session()
        fun getInstance(): Session{
            return  instance
        }
    }

    fun getActiveSession(context: Activity): Session?{
        val share = context.getPreferences(Context.MODE_PRIVATE)
        token = share.getString("seission", "")
        if(token.equals("")){
            token = null
            return null
        }

        return this
    }

    fun login(context: Activity, user: String, pass: String, call: Token) {
        val json = """{"user": "$user", "pass": "$pass"}"""
        if(token == null){
            val api = APIRest(context)
            val gson = Gson()
            val callback = object : VolleyCallback() {
                override fun onSuccessResponse(result: String) {
                    val mResponse = gson.fromJson<HashMap<String, Object>>(result, HashMap::class.java)
                    val error = mResponse["error"] as Boolean
                    if(!error){
                        val sharedPref = context.getPreferences(Context.MODE_PRIVATE) ?: return
                        with (sharedPref.edit()) {
                            putString("session", mResponse["token"] as String)
                            commit()
                        }
                    }
                    call.success(error)

                }
                override fun onError(error: String) {
                    call.error(error)
                }
            }
            api.post("http://192.168.0.2:3000/create_user", json, callback)
        }
    }
}