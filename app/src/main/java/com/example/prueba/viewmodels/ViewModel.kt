package com.example.prueba.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prueba.datamodel.User
import com.example.prueba.models.ModelDatasUser
import kotlinx.coroutines.launch

class ViewModel: ViewModel() {
    private var dataModel: ModelDatasUser? = null
    val error = MutableLiveData<String>()
    var data = MutableLiveData<User>()
    var notification = MutableLiveData<String>()
    var id = MutableLiveData<Long>()

    ////Funcion para instanciar nuestro modelo con el parametro del contexto que se recibe de la view
    private fun initInstanceDataModel(context: Context){
        ///Comprobamos si existe una instancia, en caso de no existir realizamos la instancia
        if (dataModel == null){
            dataModel = ModelDatasUser(context, this)
        }
    }

    fun login(context: Context, email: String, pass: String){
        //comprobamos si ya existe una instancia al modelo
        initInstanceDataModel(context)
        //Ejecutamos una corutina para el inicio de sesion
        viewModelScope.launch {
            dataModel?.getSession(email,pass)
        }
     }
    fun insertUser (context: Context,user: User){
        //Comprobamos la instancia al modelo
        initInstanceDataModel(context)
        //ejecutamos una corutina para realizar el registro del usuario
        viewModelScope.launch {
            dataModel?.insert(user)
        }
    }

    fun sendText (text: String, context: Context){
        //Comprobamos la instancia al modelo
        initInstanceDataModel(context)
        //Enviamos el texto que se mandara al servicio rest
        dataModel?.sendText(text)
    }
}