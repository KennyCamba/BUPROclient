package com.espol.i3lab.ideando

interface Token {
    fun success(login: Boolean)
    fun error(error: String)
}