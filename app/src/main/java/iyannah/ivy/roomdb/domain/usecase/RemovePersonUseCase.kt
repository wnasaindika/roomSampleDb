package iyannah.ivy.roomdb.domain.usecase

import iyannah.ivy.roomdb.domain.Person
import iyannah.ivy.roomdb.domain.PersonRepository
import javax.inject.Inject

class RemovePersonUseCase @Inject constructor(private val personRepository: PersonRepository)  {
    suspend operator fun invoke(person: Person) = personRepository.removePerson(person = person)
}