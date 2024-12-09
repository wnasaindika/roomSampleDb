package iyannah.ivy.roomdb.data.local.mapper

import iyannah.ivy.roomdb.data.local.model.PersonEntity
import iyannah.ivy.roomdb.domain.Person

internal fun Person.toPersonEntity(): PersonEntity {
    return PersonEntity(
        firstName = firstName,
        secondName = secondName,
        telephone = telephone
    )
}

internal fun PersonEntity.toPerson(): Person {
    return Person(
        firstName = firstName,
        secondName = secondName,
        telephone = telephone,
        id = id
    )
}