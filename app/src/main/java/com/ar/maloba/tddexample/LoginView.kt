package com.ar.maloba.tddexample

/**
 * Created by Ezequiel Maloberti on 30/7/2019.
 */
interface LoginView {

    fun showErrorMessageForUserPassword()

    fun showErrorMessageForMaxLoginAttempt()

    fun showLoginSuccessMessage()
}
