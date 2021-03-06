package com.example.prueba.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prueba.daos.DaoUser
import com.example.prueba.datamodel.User

@Database(entities = [User::class], version = 1)

abstract class DataBaseUser : RoomDatabase() {
    //Aqui creamos la funcion abstracta userDao que sera de tipo  DaoUser
    abstract fun userDao() : DaoUser

    companion object{
        private var INSTANCE: DataBaseUser? = null
        private const val DB_NAME = "user.db"

        //Aqui realizamos la instancia de la base de datos, en caso de no existir una base de datos se crea uno con el nombre user.db
        fun getDatabase(context: Context) : DataBaseUser {
            if (INSTANCE == null){
                synchronized(DataBaseUser::class.java){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(context.applicationContext, DataBaseUser::class.java, DB_NAME).build()
                    }
                }
            }
            ///Retornamos la instancia a nuestra base de datos(conexion)
            return  INSTANCE!!
        }
    }
}