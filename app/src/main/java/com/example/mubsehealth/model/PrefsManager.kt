package com.example.mubsehealth.model

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

enum class PrefsManager{
    INSTANCE;

    //constructor() : this()
    //private constructor()

    private var context: Application? = null

    constructor(application: Application) {
        this.context = application
    }

    constructor()

    fun onLogin(student: Student): Boolean{
        val sharedPreferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(KEY_USER_ID, student.id!!)
        editor.putString(KEY_FIRST_NAME, student.firstName)
        editor.putString(KEY_LAST_NAME, student.lastName)
        editor.putString(KEY_COURSE, student.course)
        editor.putString(KEY_EMAIL, student.email)
        editor.putString(KEY_STD_NO, student.stdNo)
        editor.putString(KEY_PASSWORD, student.password)
        editor.apply()
        return true
    }

    fun isLoggedIn():Boolean{
        val sharedPreferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        if (sharedPreferences.getString(KEY_EMAIL, null) != null)
            return true
        return false
    }

    fun getStudent(): Student {
        val sharedPreferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return Student(
            id = sharedPreferences.getString(KEY_USER_ID, null)!!,
            firstName = sharedPreferences.getString(KEY_FIRST_NAME, null)!!,
            lastName = sharedPreferences.getString(KEY_LAST_NAME, null)!!,
            course = sharedPreferences.getString(KEY_COURSE, null)!!,
            email = sharedPreferences.getString(KEY_EMAIL, null)!!,
            stdNo = sharedPreferences.getString(KEY_STD_NO, null)!!,
            password = sharedPreferences.getString(KEY_PASSWORD, null)!!
        )
    }

    fun onLogout(): Boolean{
        val sharedPreferences = context!!.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        return true
    }

    fun setContext(ctx: Application){
        context = ctx
    }
}
