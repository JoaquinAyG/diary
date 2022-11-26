package personal.study.diary

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import personal.study.diary.models.Contact

class ContactViewActivity : AppCompatActivity() {

    var state: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_add)

        val butSave = findViewById<Button>(R.id.butAdd)
        val name = findViewById<TextView>(R.id.name)
        val number = findViewById<TextView>(R.id.number)

        state = intent.getIntExtra(EXTRA_STATE, 0)

        when(state) {
            0 -> {
                val contact = intent.getSerializableExtra(EXTRA_CONTACT) as Contact
                name.text = contact.name
                name.isEnabled = false
                number.text = contact.phone.toString()
                number.isEnabled = false
                butSave.text = "Ok"
                butSave.setOnClickListener {
                    finish()
                }
            }
            1 -> {
                butSave.text = "Save"
                butSave.setOnClickListener {
                    if (name.text.isBlank() || number.text.isBlank()) {
                        val replyIntent = Intent()
                        setResult(RESULT_CANCELED, replyIntent)
                        finish()
                    } else {
                        val newContact =
                            Contact(-1, name.text.toString(), number.text.toString().toInt(), "")
                        val replyIntent = Intent()
                        replyIntent.putExtra(EXTRA_REPLY, newContact)
                        setResult(INSERT, replyIntent)
                        finish()

                    }
                }
            }
            2 -> {
                butSave.text = "Update"
                val contact = intent.getSerializableExtra(EXTRA_CONTACT) as Contact
                name.text = contact.name
                number.text = contact.phone.toString()
                butSave.setOnClickListener {
                    if (name.text.isBlank() || number.text.isBlank()) {
                        val replyIntent = Intent()
                        setResult(RESULT_CANCELED, replyIntent)
                        finish()
                    } else {
                        val newContact =
                            Contact(contact.id, name.text.toString(), number.text.toString().toInt(), "")
                        val replyIntent = Intent()
                        replyIntent.putExtra(EXTRA_REPLY, newContact)
                        setResult(UPDATE, replyIntent)
                        finish()
                    }
                }
            }
        }
    }
}