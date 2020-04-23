package com.calyrsoft.ejemplosinld

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel(val loginRepository: LoginRepository) : ViewModel() {

    val model: LiveData<UiModel>
        get() = _model
    private val _model = MutableLiveData<UiModel>()

    sealed class UiModel {
        class Login(val success: Boolean): UiModel()
        object Loading: UiModel()
    }

    fun doLogin(userName: String) {
        _model.value = UiModel.Loading
        val runnable = Runnable {
            _model.value = UiModel.Login( loginRepository.login(userName))
        }
        Handler().postDelayed(runnable, 3000)

    }
}