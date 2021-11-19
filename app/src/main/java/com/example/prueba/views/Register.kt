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
    ///Creamos las variables para inicializarlas despues
    lateinit var viewModel: ViewModel
    lateinit var binding : ActivityRegisterBinding
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ///Inicializamos el binding
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        binding.registerGo.setOnClickListener() {
            ////Validamos los campos que no esten vacios
            if(binding.pass.text.toString().isNotEmpty() && binding.email.text.toString().isNotEmpty()  && binding.name.text.toString().isNotEmpty()) {
                ////Validamos que el password al menos tenga 8 caracteres
                if(binding.pass.text.toString().length > 7) {
                    ////Validamos que los password coincidan
                    if (binding.pass.text.toString() == binding.confirm.text.toString()) {
                        //Una vez validado los password realizamos la insercion
                        user = User(
                            binding.name.text.toString(),
                            binding.email.text.toString(),
                            binding.phone.text.toString(),
                            binding.pass.text.toString()
                        )
                        ///En caso de haber insertado correctamente lanzamos el activity home
                        if (viewModel.insertUser(this, user) != null) {
                            startActivity(
                                Intent(
                                    this, Home::class.java
                                )

                            )
                        }
                    }
                } else {
                    Toast.makeText(this,"Tu contraseña debe contener al menos 8 digitos", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this,"Debes llenar todos los campos", Toast.LENGTH_LONG).show()
            }
        }
    }

}