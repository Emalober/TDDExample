package com.ar.maloba.tddexample

/**
 * Created by Ezequiel Maloberti on 30/7/2019.
 */
class LoginPresenter {

    companion object {
        const val MAX_LOGGIN_ATTEMPT = 3
        const val USER = "user"
        const val PASSWORD = "password"
    }

    var currentLoginAttempt: Int = 0


    fun isLogginAttemptExceeded(): Boolean {
        return currentLoginAttempt >= MAX_LOGGIN_ATTEMPT
    }

    fun newLogginAttempt(): Int {
        return ++currentLoginAttempt
    }

    fun checkUserPassword(user: String, password: String): Boolean {
        if(isLogginAttemptExceeded()) return false

        if(USER === user && PASSWORD === password)
            return true
        else {
            newLogginAttempt()
            return false
        }
    }

}