package com.example.nytimescleanarchitecture.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nytimescleanarchitecture.data.room.dto.UserDto

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(userDto: UserDto): Long?

    @Query("SELECT * FROM tbl_user WHERE user_name = :userName AND password = :password")
    fun getLoginUser(userName: String, password: String): UserDto?

    @Query("SELECT * FROM tbl_user")
    fun loadAllUsers(): List<UserDto>?
}