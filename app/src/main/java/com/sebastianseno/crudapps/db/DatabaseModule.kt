package com.sebastianseno.crudapps.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun providesRoom(context: Context): QasirDb {
        return Room.databaseBuilder(context, QasirDb::class.java, "qasir.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}