package com.example.prueba.models

import android.content.Context
import com.example.prueba.daos.DaoUser
import com.example.prueba.database.DataBaseUser
import com.example.prueba.datamodel.User
import com.example.prueba.viewmodels.ViewModel


class ModelDatasUser(private val context: Context, private val viewModel: ViewModel)  {
    private val userdao: DaoUser = DataBaseUser.getDatabase(context)?.userDao()

    suspend fun insert(user: User) {
        if (userdao != null)
            userdao.insertUser(user)?.also { viewModel.id.value = it }
    }

    suspend fun getSession( email: String, pass: String){
        if (userdao != null)
        viewModel.data.value = userdao.loginUser(email,pass)
    }
}