package com.espol.i3lab.ideando

abstract class VolleyCallback {
    abstract fun onSuccessResponse(result: String)
    abstract fun onError(error: String)
}