package personal.study.diary

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import personal.study.diary.adapters.ContactListAdapter
import personal.study.diary.factories.ContactViewModelFactory
import personal.study.diary.models.Contact
import personal.study.diary.viewmodel.ContactViewModel

class MainActivity : AppCompatActivity() {

    private val contactViewModel: ContactViewModel by viewModels {
        ContactViewModelFactory((application as ContactApplication).repository)
    }

    private val activityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        when (it.resultCode) {
            INSERT -> {
                val contact = it.data?.getSerializableExtra(EXTRA_REPLY)
                contactViewModel.insert(contact as Contact)
            }
            UPDATE -> {
                val contact = it.data?.getSerializableExtra(EXTRA_REPLY)
                contactViewModel.update(contact as Contact)
            }
            else -> Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvContacts)
        val butAdd = findViewById<ImageButton>(R.id.butAdd)
        val adapter = ContactListAdapter(
            onClick = {
                val intent = Intent(this@MainActivity, ContactViewActivity::class.java)
                intent.putExtra(EXTRA_CONTACT, it)
                intent.putExtra(EXTRA_EDITABLE, false)
                activityResultLauncher.launch(intent)
            },
            onDelete = { contactViewModel.delete(it) },
            onFavourite = { Toast.makeText(this, "Favourite", Toast.LENGTH_SHORT).show() }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        contactViewModel.allContacts.observe(this) { contact ->
            contact?.let { adapter.submitList(it) }
        }

        butAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, ContactViewActivity::class.java)
            intent.putExtra(EXTRA_EDITABLE, true)
            activityResultLauncher.launch(intent)
        }
    }
}