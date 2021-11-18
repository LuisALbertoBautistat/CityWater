package com.example.prueba.daos

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.prueba.datamodel.User

@Dao
interface DaoUser {
    @Query("SELECT * FROM user WHERE email = :email AND pass= :pass LIMIT 1")
    suspend fun loginUser(email: String, pass: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User): Long
}