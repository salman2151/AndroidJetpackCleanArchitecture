package com.example.nytimescleanarchitecture.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nytimescleanarchitecture.data.room.dto.UserDto

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(userDto: UserDto): Long?

    @Query("SELECT * FROM tbl_user WHERE user_name LIKE :userName AND password LIKE :password")
    fun getLoginUser(userName: String, password: String): UserDto
}