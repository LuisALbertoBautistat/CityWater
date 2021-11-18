package com.example.prueba.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.prueba.datamodel.User.Companion.FULL_NAME
import com.example.prueba.datamodel.User.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME,
    indices = [Index(value = [FULL_NAME], unique = true)])

data class User(
    @ColumnInfo(name = FULL_NAME) var fullName: String,
    @ColumnInfo(name = "email") var email:String,
    @ColumnInfo(name = "phone") var phone:String,
    @ColumnInfo(name = "pass") var pass:String
){
    companion object{
        const val TABLE_NAME = "user"
        const val FULL_NAME = "full_name"
    }
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}
