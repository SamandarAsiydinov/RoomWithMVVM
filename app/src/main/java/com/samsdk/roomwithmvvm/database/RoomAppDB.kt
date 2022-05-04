package com.samsdk.roomwithmvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class RoomAppDB : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var INSTANCE: RoomAppDB? = null

        fun getAppDatabase(context: Context): RoomAppDB {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RoomAppDB::class.java,
                    "user.db"
                ).allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}