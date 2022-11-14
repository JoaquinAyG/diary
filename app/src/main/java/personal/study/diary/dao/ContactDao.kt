package personal.study.diary.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import personal.study.diary.models.Contact

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): List<Contact>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contact: Contact)

    @Query("DELETE FROM contacts")
    fun deleteAll()
}