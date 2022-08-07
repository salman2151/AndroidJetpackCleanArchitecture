package com.example.nytimescleanarchitecture.data.room.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_user")
data class UserDto (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val id:Long =0,
    @ColumnInfo(name = "user_name")
    val userName:String,
    @ColumnInfo(name = "password")
    val password:String,

    )