package personal.study.diary.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import personal.study.diary.models.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactRoomDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ContactRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactRoomDatabase::class.java,
                    "contact_database"
                ).addCallback(ContactDatabaseCallback(scope)).allowMainThreadQueries().build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class ContactDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.contactDao())
                }
            }
        }

        fun populateDatabase(contactDao: ContactDao) {

            contactDao.deleteAll()

            val contact = Contact(1, "Hello", 43110, "")
            contactDao.insert(contact)
        }
    }
}