package com.example.prueba.models

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.VolleyLog.TAG
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.prueba.R
import com.example.prueba.daos.DaoUser
import com.example.prueba.database.DataBaseUser
import com.example.prueba.datamodel.User
import com.example.prueba.viewmodels.ViewModel


class ModelDatasUser(private val context: Context, private val viewModel: ViewModel)  {
    ///Realizamos la instancia a nuestra base de datos implementando la funcion abstracta userDao
    private val userdao: DaoUser = DataBaseUser.getDatabase(context)?.userDao()

    //Funcion para realizar la insercion de un usuario utilizando el Dao
    suspend fun insert(user: User) {
        if (userdao != null)
            userdao.insertUser(user)?.also { viewModel.id.value = it }
    }

    suspend fun getSession( email: String, pass: String){
        if (userdao != null)
        viewModel.data.value = userdao.loginUser(email,pass)
    }

    fun sendText (text: String){
        ////Aqui concatenamos el server definido previamente en el recurso String junto al path que se va utilizar
        val url = context.resources.getString(R.string.server_remote) + context.resources.getString(R.string.path)
        ////Instanciamos Volley
        val queue = Volley.newRequestQueue(context)
        ///Creamos la configuracion del request
        val stringRequest = StringRequest(
            Request.Method.GET, url + text,
            { response ->
                    viewModel.notification.value = "Su comentario se ha subido al servidor"
                Log.e(TAG, "Este es el response "+ response.toString())

            },
            {
                Log.e(TAG, "Este el error que retorna "+it.toString())
                viewModel.error.value = "No se pudo enviar su comentario"
            })
        // Agregamos la configuracion al queue para lanzar la peticion
        queue.add(stringRequest)
    }

}