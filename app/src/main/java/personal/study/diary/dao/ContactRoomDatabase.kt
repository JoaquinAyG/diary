package personal.study.diary.dao

import android.content.Context
import android.os.AsyncTask.execute
import androidx.room.Database
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import personal.study.diary.models.Contact
import java.util.concurrent.Executors
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
                ).addCallback(ContactDatabaseCallback(scope)).build()
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

            var contact = Contact(1, "Hello", 43110, "")
            contactDao.insert(contact)
        }
    }
}