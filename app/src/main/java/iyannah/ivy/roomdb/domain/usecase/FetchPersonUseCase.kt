package iyannah.ivy.roomdb.domain.usecase

import iyannah.ivy.roomdb.domain.PersonRepository
import javax.inject.Inject

class FetchPersonUseCase @Inject constructor(private val personRepository: PersonRepository) {
    operator fun invoke() = personRepository.getAllPersons()
}