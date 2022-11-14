package personal.study.diary.models

import androidx.room.Entity

@Entity(tableName = "contacts")
class Contact() {

    var id: Int = -1
    var name: String? = null
    var phone: String? = null
    var image: String? = null

    constructor(id: Int?, name: String?, phone: String?, image: String?) : this() {
        this.id = id?:-1
        this.name = name
        this.phone = phone
        this.image = image
    }

}