package com.example.apparchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.apparchitecture.databinding.ActivityMainBinding
import java.lang.IllegalArgumentException
import java.util.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listener = View.OnClickListener {
            val id = when (it.id) {
                R.id.btn_1 -> Buttons.FIRST
                R.id.btn_2 -> Buttons.SECOND
                R.id.btn_3 -> Buttons.THIRD
                else -> throw IllegalArgumentException("")
            }
            presenter.counterClick(id)
        }

        binding.btn1.setOnClickListener(listener)
        binding.btn2.setOnClickListener(listener)
        binding.btn3.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String) {
        when (index) {
            0 -> binding.btn1.text = text
            1 -> binding.btn2.text = text
            2 -> binding.btn3.text = text
        }
    }
}
