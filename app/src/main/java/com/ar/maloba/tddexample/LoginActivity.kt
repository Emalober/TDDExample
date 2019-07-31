package com.ar.maloba.tddexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.util.Log


class LoginActivity : AppCompatActivity() , LoginView{

    private val LOG_TAG_UI: String = "LoginActivity"
    private val USER_INPUTTEXT: String = "userText_input"

    private lateinit var loginPresenter: LoginPresenter
    private lateinit var txtUserName: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initializePresenter()
        initializeViews()

        if(savedInstanceState!=null) {
            Log.d(LOG_TAG_UI, "Main activity onCreate savedInstanceState is not null.")
            val user = savedInstanceState.getString(USER_INPUTTEXT)
            txtUserName.setText(user)
        } else {
            Log.d(LOG_TAG_UI, "Main activity onCreate savedInstanceState is null.");
        }
    }

    // This method will be invoked before onStop() method.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val user = txtUserName.getText().toString()
        outState.putString(USER_INPUTTEXT, user)
        Log.d(LOG_TAG_UI, "Main activity onSaveInstanceState.")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val user = savedInstanceState.getString(USER_INPUTTEXT)
        Log.d(LOG_TAG_UI, "Main activity onRestoreInstanceState.")
    }

    private fun initializePresenter() {
        loginPresenter = LoginPresenter(this)
    }

    private fun initializeViews() {
        txtUserName = findViewById(R.id.txt_user_name)
        txtPassword = findViewById(R.id.txt_password)
        btnLogin = findViewById(R.id.btn_login)
        btnLogin.setOnClickListener {
            val isLogged = loginPresenter.checkUserPassword(
                txtUserName.text.toString().trim(),
                txtPassword.text.toString().trim()
            )
            if(isLogged) {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
            }
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
