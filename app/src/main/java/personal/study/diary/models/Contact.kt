package personal.study.diary.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
class Contact(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "phone") var phone: Int,
    @ColumnInfo(name = "image") var image: String,

) {

    constructor() : this(
        -1,
        "",
        0,
        ""
    )

}