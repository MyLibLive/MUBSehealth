package com.example.mubsehealth.model

class Booking {
    var date:String ?= null
    var time:String ?= null
    var purpose:String ?= null
    var dName:String ?= null
    var dPhone:String ?= null
    constructor(
                date:String,
                time:String,
                purpose:String,
                dName:String,
                dPhone:String,
                ){
        this.date=date
        this.time=time
        this.purpose=purpose
        this.dName = dName
        this.dPhone = dPhone
    }

    constructor()
}