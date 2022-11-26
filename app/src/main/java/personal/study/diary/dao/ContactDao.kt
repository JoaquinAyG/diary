package personal.study.diary.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import personal.study.diary.models.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(contact: Contact)

    @Delete
    fun delete(contact: Contact)

    @Update
    fun update(contact: Contact)

    @Query("DELETE FROM contacts")
    fun deleteAll()

}