package com.ugdgomezdiez.tresenraya.feature.tresenraya.data

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room.GameDao
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room.GameEntity
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room.TurnDao
import com.ugdgomezdiez.tresenraya.feature.tresenraya.data.room.TurnEntity

@Database(
    entities = [GameEntity::class, TurnEntity::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun turnDao(): TurnDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database").build()
                INSTANCE = instance
                instance
            }

        }
    }
}