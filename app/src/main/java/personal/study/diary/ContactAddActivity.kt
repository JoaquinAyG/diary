package personal.study.diary

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import personal.study.diary.models.Contact


class ContactAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_add)

        val butSave = findViewById<Button>(R.id.butAdd)
        val name = findViewById<TextView>(R.id.name)
        val number = findViewById<TextView>(R.id.number)

        val contact = intent?.getSerializableExtra("contact") as Contact
        val editable = intent?.getBooleanExtra("editable", false)


        
    }
}