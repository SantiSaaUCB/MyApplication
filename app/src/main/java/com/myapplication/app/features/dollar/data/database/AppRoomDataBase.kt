package com.myapplication.app.features.dollar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.myapplication.app.features.dollar.data.database.dao.IDollarDao
import com.myapplication.app.features.dollar.data.database.entity.DollarEntity

@Database(entities = [DollarEntity::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun dollarDao(): IDollarDao
    companion object {
        @Volatile
        private var Instance: AppRoomDatabase? = null
        fun getDatabase(context: Context): AppRoomDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppRoomDatabase::class.java, "dollar_db")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
