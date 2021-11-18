package com.example.prueba.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.prueba.databinding.ActivityMainViewBinding
import com.example.prueba.viewmodels.ViewModel


class MainView : AppCompatActivity() {
    lateinit var binding: ActivityMainViewBinding
    lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.data.observe(this,{
            if(it != null){
                startActivity(
                    Intent(
                        this, Home::class.java
                    )
                )
            } else {
                Log.e( "TAG_ERROR", "No se encuentra en la base de datos")
            }
        })
        viewModel.error.observe(this,{

        })

        binding.login.setOnClickListener(){
            val pass = binding.pass.text.toString()
            val email = binding.user.text.toString()
            viewModel.login(this, email,pass)
        }

        binding.register.setOnClickListener(){
            startActivity(
                Intent(
                    this, Register::class.java
                )
            )
        }
    }
}