package personal.study.diary.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import personal.study.diary.R
import personal.study.diary.models.Contact


class ContactListAdapter(
    private val onFavourite: (Contact) -> Unit,
    private val onClick: (Contact) -> Unit,
    private val onDelete: (Contact) -> Unit,
    private val onEdit: (Contact) -> Unit
) : ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(ContactsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = ViewGroup.inflate(parent.context, R.layout.item_contact, null)
        return ContactViewHolder(view, onClick, onFavourite, onDelete, onEdit)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ContactViewHolder(
        itemView: View,
        private val onClick: (Contact) -> Unit,
        private val onFavourite: (Contact) -> Unit,
        private val onDelete: (Contact) -> Unit,
        private val onEdit: (Contact) -> Unit

    ) : ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.name)
        private val number: TextView = itemView.findViewById(R.id.number)
        private val image: ImageView = itemView.findViewById(R.id.image)
        private val burDelete: ImageButton = itemView.findViewById(R.id.delete)
        private val butFavourite: ImageButton = itemView.findViewById(R.id.fav)
        private val butEdit: ImageButton = itemView.findViewById(R.id.edit)

        fun bind(contact: Contact) {
            name.text = contact.name
            number.text = contact.phone.toString()
            image.setImageResource(R.mipmap.profile_icon)
            itemView.setOnClickListener { onClick(contact) }
            burDelete.setOnClickListener { onDelete(contact) }
            butFavourite.setOnClickListener { onFavourite(contact) }
            butEdit.setOnClickListener { onEdit(contact) }
        }
    }

    class ContactsComparator : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
