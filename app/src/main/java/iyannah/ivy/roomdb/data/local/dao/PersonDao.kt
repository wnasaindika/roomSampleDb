package iyannah.ivy.roomdb.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import iyannah.ivy.roomdb.data.local.model.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerson(person: PersonEntity): Long

    @Upsert
    suspend fun updatePerson(person: PersonEntity)

    @Delete
    suspend fun deletePerson(person: PersonEntity)

    @Query("DELETE FROM personTable WHERE id = :id")
    suspend fun deletePersonById(id: Int)

    @Query("SELECT * FROM personTable")
    fun getAllPersons(): Flow<List<PersonEntity>>
}