package com.example.nytimescleanarchitecture.user_tests

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.nytimescleanarchitecture.data.room.dao.UserDao
import com.example.nytimescleanarchitecture.data.room.database.NyTimesDatabase
import com.example.nytimescleanarchitecture.data.room.dto.UserDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class RoomTestForUser {


    private lateinit var userDatabase: NyTimesDatabase
    private lateinit var userDao: UserDao


    // execute before every test case
    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        userDatabase = Room.inMemoryDatabaseBuilder(context, NyTimesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userDao = userDatabase.userDao()
    }

    @Test
    fun addUser() = kotlin.run {
        val userEntity = UserDto(0, "Salman2151", "Qwe123")
        val userId = userDao.insert(userEntity)
        assertThat(userId != null && userId != 0L, equalTo(true))

    }

}