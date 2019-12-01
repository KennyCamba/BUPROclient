package com.espol.i3lab.ideando

import android.content.Context
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject


class APIRest(context: Context) {
    private val queue: RequestQueue = Volley.newRequestQueue(context)

    fun post(url: String, values: String?, callback: VolleyCallback){
        val request = object: StringRequest(Method.POST, url,
            Response.Listener<String> { response ->
                callback.onSuccessResponse(response)
            }, Response.ErrorListener { error ->
                callback.onError(error.toString())
            }){
                override fun getParams(): Map<String, String> {
                    val gson = Gson()
                    val params = gson.fromJson<HashMap<String, String>>(values, HashMap::class.java)
                    return params
                }
            }
        queue.add(request)
    }

}