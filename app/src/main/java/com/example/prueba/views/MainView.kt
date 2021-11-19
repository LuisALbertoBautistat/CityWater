package com.example.prueba.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.prueba.databinding.ActivityMainViewBinding
import com.example.prueba.sendmail.MailService
import com.example.prueba.viewmodels.ViewModel
import papaya.`in`.sendmail.SendMail


class MainView : AppCompatActivity() {
    lateinit var binding: ActivityMainViewBinding
    lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        ///Aqui estamos la libreria de un tercero
        val mail = SendMail(
                /////Esto es la configuracion del emisor
            "pruebacitywater@gmail.com", "pruebacity",
                ////Este es el correo del receptor
            "abasolojonas@gmail.com",
                ///Este es el asunto
            "Prueba city water",
                ////Este es el mensaje o el cuerpo del correo
            "Te acabas de resgitras a city water"
        )

        ////aqui observamos data
        viewModel.data.observe(this, {
            if (it != null) {
                ///Como prueba se ejecuta el mail, esto no deberia ir aca pero igual tampoco funciono
                mail.execute()
                startActivity(
                    Intent(
                        this, Home::class.java
                    )
                )
            } else {
                Log.e("TAG_ERROR", "No se encuentra en la base de datos")
            }
        })
        //Evento el boton login
        binding.login.setOnClickListener(){
            ///Al presionar el boton login obtemos los datos que tenemos en los campos email y passwprd
            val pass = binding.pass.text.toString()
            val email = binding.email.text.toString()
            ///Le realizamos la peticion al viwModel con los parametros requeridos
            viewModel.login(this, email, pass)
        }

        ///Evento del boton registrarse
        binding.register.setOnClickListener(){
            //Al dar click al boton registrar lanzamos la actividad para el registro
            startActivity(
                Intent(
                    this, Register::class.java
                )
            )
        }

    }
}