package iyannah.ivy.roomdb.data

import iyannah.ivy.roomdb.data.local.dao.PersonDao
import iyannah.ivy.roomdb.data.local.mapper.toPerson
import iyannah.ivy.roomdb.data.local.mapper.toPersonEntity
import iyannah.ivy.roomdb.domain.Person
import iyannah.ivy.roomdb.domain.PersonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PersonRepositoryImpl @Inject constructor(private val personDao: PersonDao) :
    PersonRepository {
    override suspend fun addPerson(person: Person): Long {
        return personDao.insertPerson(person = person.toPersonEntity())
    }

    override suspend fun updatePerson(person: Person) {
        personDao.updatePerson(person = person.toPersonEntity())
    }

    override fun getAllPersons(): Flow<List<Person>> {
        return personDao.getAllPersons().map { it.map { it.toPerson() } }
    }

    override suspend fun removePerson(person: Person) {
        person.id?.let {
            personDao.deletePersonById(id = person.id)
        }
    }
}