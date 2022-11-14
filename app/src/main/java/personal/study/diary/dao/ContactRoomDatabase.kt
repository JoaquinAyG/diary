package personal.study.diary.dao

import android.content.Context
import android.os.AsyncTask.execute
import androidx.room.Database
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import personal.study.diary.models.Contact
import java.util.concurrent.Executors
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactRoomDatabase {

    abstract fun contactDao(): ContactDao

    @Volatile
    private var INSTANCE: ContactRoomDatabase? = null
    private val NUMBER_OF_THREADS = 4
    val databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

    open fun getDatabase(context: Context): ContactRoomDatabase? {
        if (INSTANCE == null) {
            synchronized(ContactRoomDatabase::class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ContactRoomDatabase::class, "word_database"
                    )
                        .addCallback(sRoomDatabaseCallback)
                        .build()
                }
            }
        }
        return INSTANCE
    }

    private val sRoomDatabaseCallback: RoomDatabase.Callback = object : Callback() {
        fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            execute {

                // Populate the database in the background.
                // If you want to start with more words, just add them.
                val dao: ContactDao? = INSTANCE?.contactDao()
                dao?.deleteAll()
                var word: Word? = Word("Hello")
                dao.insert(word)
                word = Word("World")
                dao.insert(word)
            }
        }
    }
}