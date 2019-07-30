package com.ar.maloba.tddexample

/**
 * Created by Ezequiel Maloberti on 30/7/2019.
 */
class LoginPresenter(private val loginView: LoginView) {

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

        if(isLogginAttemptExceeded()){
            loginView.showErrorMessageForMaxLoginAttempt()
            return false
        }

        if(USER === user && PASSWORD === password)
            loginView.showLoginSuccessMessage()
        else {
            loginView.showErrorMessageForUserPassword()
            newLogginAttempt()
            return false
        }
        return true
    }

}