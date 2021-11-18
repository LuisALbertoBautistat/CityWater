package com.example.prueba.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.prueba.databinding.ActivityRegisterBinding
import com.example.prueba.datamodel.User
import com.example.prueba.viewmodels.ViewModel

class Register : AppCompatActivity() {
    lateinit var viewModel: ViewModel
    lateinit var binding : ActivityRegisterBinding
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        binding.registerGo.setOnClickListener() {
            if(binding.pass.text != null && binding.email.text != null && binding.name != null) {
                if (binding.pass.text.toString() == binding.confirm.text.toString()) {
                    user = User(
                        binding.name.text.toString(),
                        binding.email.text.toString(),
                        binding.phone.text.toString(),
                        binding.pass.text.toString()
                    )

                    if (viewModel.insertUser(this, user) != null) {
                        startActivity(
                            Intent(
                                this, Home::class.java
                            )
                        )
                    }
                }
            } else {
                Toast.makeText(this,"Debes llenar los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

}