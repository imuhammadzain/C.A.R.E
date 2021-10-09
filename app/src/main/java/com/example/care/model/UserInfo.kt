package com.example.care.model

class UserInfo{
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var stdEmail: String
    lateinit var phone: String
    constructor(){}
    constructor(firstName: String,lastName: String,stdEmail: String,phone: String){

        this.firstName=firstName
        this.lastName=lastName
        this.stdEmail=stdEmail
        this.phone=phone
    }
}

