package personal.study.diary.dao

import androidx.annotation.WorkerThread
import personal.study.diary.models.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao){
    val allContacts: Flow<List<Contact>> = contactDao.getAllContacts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }

    suspend fun delete(contact: Contact) {
        contactDao.delete(contact)
    }

    suspend fun update(contact: Contact) {
        contactDao.update(contact)
    }
}