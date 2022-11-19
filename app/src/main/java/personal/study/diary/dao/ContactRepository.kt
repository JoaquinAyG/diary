package personal.study.diary.dao

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import personal.study.diary.models.Contact

class ContactRepository(private val contactDao: ContactDao) {
    val allContacts: Flow<List<Contact>> = contactDao.getAllContacts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }

    fun delete(contact: Contact) {
        contactDao.delete(contact)
    }

    fun update(contact: Contact) {
        contactDao.update(contact)
    }
}