package iyannah.ivy.roomdb.presentation

import iyannah.ivy.roomdb.domain.Person

sealed interface PersonEvent {
    data class OnSaveClicked(val firstName: String, val secondName: String, val telephone: String) :
        PersonEvent

    data class OnRemove(val person: Person) : PersonEvent
}