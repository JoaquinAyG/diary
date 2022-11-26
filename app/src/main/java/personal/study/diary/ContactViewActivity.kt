package personal.study.diary

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import personal.study.diary.models.Contact

class ContactViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_add)

        val butSave = findViewById<Button>(R.id.butAdd)
        val name = findViewById<TextView>(R.id.name)
        val number = findViewById<TextView>(R.id.number)

        if(!intent.getBooleanExtra(EXTRA_EDITABLE, false)) {
            val contact = intent.getSerializableExtra(EXTRA_CONTACT) as Contact
            name.text = contact.name
            name.isEnabled = false
            number.text = contact.phone.toString()
            number.isEnabled = false
            butSave.setOnClickListener {
                finish()
            }
        }

        butSave.setOnClickListener{
            if(name.text.isEmpty() && number.text.isEmpty()) {
                Toast.makeText(this, "Please enter a name and number", Toast.LENGTH_SHORT).show()
            } else {
                val newContact = Contact(15, name.text.toString(), number.text.toString().toInt(), "")
                val replyIntent = Intent()
                replyIntent.putExtra(EXTRA_REPLY, newContact)
                setResult(INSERT, replyIntent)
                finish()
            }
        }

    }
}