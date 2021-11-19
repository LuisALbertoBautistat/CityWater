package com.example.prueba.views

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.prueba.R
import com.example.prueba.databinding.ActivityHomeBinding
import com.example.prueba.service.Service
import com.example.prueba.viewmodels.ViewModel


class Home : AppCompatActivity() {
    //Creacion de las variables
    lateinit var binding : ActivityHomeBinding
    var service:Service = Service()
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Inicializamos la varible binding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ///Inicializamos la variable viewModel
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        ////Creando el canal de notificaciones
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = getString(R.string.default_notification_channel_name)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(
                NotificationChannel(
                    channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW
                )
            )
        }
        ///Observamos el cambio para realizar la notificacion
        viewModel.notification.observe(this,{
            //Para levantar la notificacion push utilizamos hilos y le decimos que se ejecutara despues de 60 segundos de que se halla cambiado el atributo notification en el viewModel
            Thread{
                Thread.sleep(60000)
                service.sendNotification(this,it)
            }.start()
        })
        //Observamos los cambios en error
        viewModel.error.observe(this, {
            Toast.makeText(this,it, Toast.LENGTH_LONG).show()
        })
        ////Escuchamos el evento onclick del boton sendTo para ejecutar la funcion al momento de ser pulsado
        binding.sendTo.setOnClickListener() {
            Log.e("TAG", binding.send.text.toString())
            viewModel.sendText(binding.send.text.toString(), this)
        }

    }
}