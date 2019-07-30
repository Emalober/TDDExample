package com.ar.maloba.tddexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() , LoginView{

    private lateinit var loginPresenter: LoginPresenter
    private lateinit var txtUserName: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initializePresenter()
        initializeViews()
    }

    private fun initializePresenter() {
        loginPresenter = LoginPresenter(this)
    }

    private fun initializeViews() {
        txtUserName = findViewById(R.id.txt_user_name)
        txtPassword = findViewById(R.id.txt_password)
        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            loginPresenter.checkUserPassword(
                txtUserName.text.toString().trim(),
                txtPassword.text.toString().trim()
            )
        }
    }

    override fun showErrorMessageForUserPassword() {
        Snackbar.make(txtPassword, R.string.error_user_password, Snackbar.LENGTH_LONG).show()
    }

    override fun showErrorMessageForMaxLoginAttempt() {
        Snackbar.make(btnLogin, R.string.error_max_login_attempt, Snackbar.LENGTH_LONG).show()
    }

    override fun showLoginSuccessMessage() {
        Snackbar.make(btnLogin, R.string.login_ok, Snackbar.LENGTH_LONG).show();
    }
}
