package com.calyrsoft.ejemplosinld

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var liveDataString: LiveDataString
    var counter: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        liveDataString = LiveDataString()

        liveDataString.cadena.observe(this, Observer(::updateUi))
        add_btn.setOnClickListener {
            counter++
            liveDataString.cambiarValor(counter.toString())
        }
    }

    private fun updateUi(s: String?) {
        number_text_view.text = s
    }
}

class LiveDataString: ViewModel() {
    val cadena : LiveData<String>
        get() = _cadena

    private val _cadena = MutableLiveData<String>()

    fun cambiarValor(string: String) {
        _cadena.value = string
    }
}
