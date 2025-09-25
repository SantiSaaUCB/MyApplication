package com.myapplication.app.features.movies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapplication.app.features.movies.data.database.dao.IMovieDao
import com.myapplication.app.features.movies.data.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppRoomDataBase : RoomDatabase() {
    abstract fun movieDao(): IMovieDao
}