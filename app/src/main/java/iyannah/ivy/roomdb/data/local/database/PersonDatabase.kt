package iyannah.ivy.roomdb.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import iyannah.ivy.roomdb.data.local.dao.PersonDao
import iyannah.ivy.roomdb.data.local.model.PersonEntity

@Database(
    entities = [PersonEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PersonDatabase: RoomDatabase() {
    abstract fun getDao(): PersonDao
}