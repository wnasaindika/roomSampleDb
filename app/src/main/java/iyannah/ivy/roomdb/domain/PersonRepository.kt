package iyannah.ivy.roomdb.domain

import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    suspend fun addPerson(person: Person): Long
    suspend fun updatePerson(person: Person)
    fun getAllPersons(): Flow<List<Person>>
    suspend fun removePerson(person: Person)
}