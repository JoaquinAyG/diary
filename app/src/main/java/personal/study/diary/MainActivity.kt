package personal.study.diary

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import personal.study.diary.adapters.ContactListAdapter
import personal.study.diary.factories.ContactViewModelFactory
import personal.study.diary.models.Contact
import personal.study.diary.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {

    private val newContactActivityRequestCode = 1

    private val contactViewModel: ContactViewModel by viewModels {
        ContactViewModelFactory((application as ContactApplication).repository)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newContactActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getSerializableExtra(EXTRA_REPLY)?.let {
                contactViewModel.insert(it as Contact)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvContacts)
        val butAdd = findViewById<ImageButton>(R.id.butAdd)
        val adapter = ContactListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        contactViewModel.allContacts.observe(this) { contact ->
            contact?.let { adapter.submitList(it) }
        }

    }
}