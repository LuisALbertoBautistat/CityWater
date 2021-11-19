package com.example.prueba.sendmail

import java.util.Properties
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import kotlin.concurrent.thread

class MailService {
    ///Funcion para mandar el correo PD no funciono por las autenticaciones en google
    fun send() {
        ///creamos un hilo para que sea algo asincrono
        thread {
            //Realizamos las configuraciones
            val property = Properties()
            property.put("mail.smtp.host", "smtp.gmail.com")
            property.put("mail.smtp.auth", "true")
            property.put("mail.smtp.starttls.enable", "true")
            property.put("mail.smtp.port", "587")
            ///Declaramos el correo desde donde se va a realizar el envio al igual que la password
            var userName = "pruebacitywater@gmail.com"
            var password = "pruebacity"

            ///Nos autenticamos y guardamos la aunteticacion en la variable message
            val message: Message = MimeMessage(Session.getDefaultInstance(property, object: Authenticator() {
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(userName, password)
                }
            }))
            ///Le decimos quien es el emisor
            message.setFrom(InternetAddress(userName))
            ///Definimos quien sera l receptor, aqui deberia recibir un parametro la funcion, lo pongo estatico por las pruebas realizadas y no se logro
            message.setRecipient(Message.RecipientType.TO, InternetAddress("abasolojonas@gmail.com"))
            ///Definimos el asunto del correo
            message.subject = "Registro"
            ////Agregamos el mensaje o cuerpo del correo
            message.setText("Usted se acaba de registrar en aplicacion de City Water")
            ///Realizamos el envio del correo
            Transport.send(message)
        }
    }
}