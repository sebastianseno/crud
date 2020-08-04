package com.sebastianseno.crudapps.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sebastianseno.crudapps.db.dao.BannerDao
import com.sebastianseno.crudapps.db.dao.ProductDao
import com.sebastianseno.crudapps.db.entities.BannerDb
import com.sebastianseno.crudapps.db.entities.ProductDb

@Database(
    entities = [
        ProductDb::class,
        BannerDb::class],
    version = 2,
    exportSchema = false
)

abstract class QasirDb : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun bannerDao(): BannerDao
}