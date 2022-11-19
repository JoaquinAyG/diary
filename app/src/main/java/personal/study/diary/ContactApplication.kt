package personal.study.diary

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import personal.study.diary.dao.ContactRepository
import personal.study.diary.dao.ContactRoomDatabase

class ContactApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { ContactRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { ContactRepository(database.contactDao()) }
}