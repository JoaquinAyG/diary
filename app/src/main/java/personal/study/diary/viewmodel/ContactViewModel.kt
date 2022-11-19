package personal.study.diary.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import personal.study.diary.dao.ContactRepository
import personal.study.diary.models.Contact

class ContactViewModel(private val repository: ContactRepository): ViewModel() {

    val allContacts: LiveData<List<Contact>> = repository.allContacts.asLiveData()

    fun insert(contact: Contact) = viewModelScope.launch {
        repository.insert(contact)
    }

    fun delete(contact: Contact) = viewModelScope.launch {
        repository.delete(contact)
    }

    fun update(contact: Contact) = viewModelScope.launch {
        repository.update(contact)
    }
}
