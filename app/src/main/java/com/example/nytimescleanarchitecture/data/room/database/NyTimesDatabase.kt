package com.example.nytimescleanarchitecture.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nytimescleanarchitecture.data.room.dao.UserDao
import com.example.nytimescleanarchitecture.data.room.dto.UserDto

@Database(entities = [UserDto::class], version = 1)
abstract class NyTimesDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}