package iyannah.ivy.roomdb.presentation

import iyannah.ivy.roomdb.domain.Person

data class PersonUiState(
    val personList: List<Person> = emptyList(),
    val isNameError: String? = null,
)