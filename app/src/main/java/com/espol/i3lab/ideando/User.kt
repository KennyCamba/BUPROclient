package com.espol.i3lab.ideando

class User {
    var user: String
    var pass: String
    var name: String
    var lastname: String
    var email: String
    var phone: String
    var birthdate: String
    var genero: String

    constructor(user: String, pass: String){
        this.user = user
        this.pass = pass
        this.name = ""
        this.lastname = ""
        this.email = ""
        this.phone = ""
        this.birthdate = ""
        this.genero = ""
    }

    constructor(
        user: String,
        pass: String,
        name: String,
        lastname: String,
        email: String,
        phone: String,
        birthdate: String,
        genero: String
    ) {
        this.user = user
        this.pass = pass
        this.name = name
        this.lastname = lastname
        this.email = email
        this.phone = phone
        this.birthdate = birthdate
        this.genero = genero
    }

    override fun toString(): String {
        return "User(name='$name', lastname='$lastname', email='$email', phone='$phone', birthdate='$birthdate', genero='$genero')"
    }
}