package com.calyrsoft.ejemplosinld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginViewModel = LoginViewModel(LoginRepository())
        loginViewModel.model.observe(this, Observer(::updateUi))

        login_btn.setOnClickListener {
            loginViewModel.doLogin(username_edit_text.text.toString())
        }
    }

    private fun updateUi(model: LoginViewModel.UiModel?) {
        loading_progress_bar.visibility = if ( model is LoginViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when ( model ) {
            is LoginViewModel.UiModel.Login -> validarLogin(model.success)
        }
    }

    private fun validarLogin( resp: Boolean) {
        if( resp) {
            Toast.makeText(this, "Exito", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "No exitoso", Toast.LENGTH_LONG).show()
        }

    }

}
