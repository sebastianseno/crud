package com.sebastianseno.crudapps.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<in T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @JvmSuppressWildcards
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: List<T>?)

    @JvmSuppressWildcards
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertIgnore(obj: List<T>)

    @Update
    fun update(obj: T)

    @JvmSuppressWildcards
    @Update
    fun update(obj: List<T>)

    @Delete
    fun delete(obj: T)
}