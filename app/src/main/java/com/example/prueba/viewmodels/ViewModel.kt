package com.example.prueba.viewmodels

import android.content.Context
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
    var id = MutableLiveData<Long>()

    private fun initInstanceDataModel(context: Context){
        if (dataModel == null){
            dataModel = ModelDatasUser(context, this)
        }
    }

    fun login(context: Context, email: String, pass: String){
        initInstanceDataModel(context)
        viewModelScope.launch {
            dataModel?.getSession(email,pass)
        }
     }
    fun insertUser (context: Context,user: User){
        initInstanceDataModel(context)
        viewModelScope.launch {
            dataModel?.insert(user)
        }
    }
}