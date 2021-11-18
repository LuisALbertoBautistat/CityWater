package com.example.prueba.views

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.prueba.R
import com.example.prueba.databinding.ActivityHomeBinding
import com.example.prueba.service.Service


class Home : AppCompatActivity() {
    lateinit var binding : ActivityHomeBinding
    var service:Service = Service()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
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

        intent.extras?.let {
            for (key in it.keySet()) {
                val value = intent.extras?.get(key)
            }
        }

        binding.sendTo.setOnClickListener() {
            service.sendNotification(this,"Acabas de enviar tu comentario")

        }

    }
}