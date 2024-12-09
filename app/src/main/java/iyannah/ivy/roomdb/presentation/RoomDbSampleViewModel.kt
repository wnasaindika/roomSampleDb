package iyannah.ivy.roomdb.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import iyannah.ivy.roomdb.domain.Person
import iyannah.ivy.roomdb.domain.usecase.AddPersonUseCase
import iyannah.ivy.roomdb.domain.usecase.FetchPersonUseCase
import iyannah.ivy.roomdb.domain.usecase.RemovePersonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomDbSampleViewModel @Inject constructor(
    private val addPersonUseCase: AddPersonUseCase,
    private val fetchPersonUseCase: FetchPersonUseCase,
    private val removePersonUseCase: RemovePersonUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(PersonUiState())
    val state = _state.asStateFlow()

    init {
        fetchPerson()
    }

    fun onEvent(event: PersonEvent) {
        when (event) {
            is PersonEvent.OnSaveClicked -> {
                if (event.firstName.isBlank() || event.secondName.isBlank() || event.telephone.isBlank()) {
                    _state.update {
                        it.copy(
                            isNameError = "Field cannot be empty"
                        )
                    }
                } else {
                    addPerson(
                        firstName = event.firstName,
                        secondName = event.secondName,
                        telephone = event.telephone
                    )
                    fetchPerson()
                }
            }

            is PersonEvent.OnRemove -> {
                removePerson(event.person)
                fetchPerson()
            }
        }
    }

    private fun addPerson(firstName: String, secondName: String, telephone: String) {
        viewModelScope.launch {
            addPersonUseCase.invoke(
                Person(
                    firstName = firstName,
                    secondName = secondName,
                    telephone = telephone
                )
            )
        }
    }

    private fun fetchPerson() {
        viewModelScope.launch {
            fetchPersonUseCase.invoke().onEach { response ->
                _state.update {
                    it.copy(
                        personList = response
                    )
                }
            }.collect()
        }
    }

    private fun removePerson(person: Person) {
        viewModelScope.launch {
            removePersonUseCase.invoke(person)
        }
    }
}