package com.samsdk.roomwithmvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.samsdk.roomwithmvvm.database.RoomAppDB
import com.samsdk.roomwithmvvm.database.UserEntity


class MainViewModel(app: Application) : AndroidViewModel(app) {

    var allUsers: MutableLiveData<List<UserEntity>> = MutableLiveData()
    val userDao = RoomAppDB.getAppDatabase(getApplication()).userDao()

    fun getAllUsersObserver(): MutableLiveData<List<UserEntity>> {
        return allUsers
    }

    fun getAllUsers() {
        val list = userDao.getAllUserInfo()
        allUsers.postValue(list)
    }

    fun insertUserInfo(user: UserEntity) {
        userDao.insertUser(user)
        getAllUsers()
    }
    fun updateUserInfo(user: UserEntity) {
        userDao.updateUser(user)
        getAllUsers()
    }
    fun deleteUser(user: UserEntity) {
        userDao.deleteUser(user)
        getAllUsers()
    }
}