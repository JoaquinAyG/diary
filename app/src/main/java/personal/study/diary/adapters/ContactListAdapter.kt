package personal.study.diary.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import personal.study.diary.models.Contact


class ContactListAdapter : ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(ContactsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(personal.study.diary.R.id.name)
        private val number: TextView = itemView.findViewById(personal.study.diary.R.id.number)
        private val image: ImageView = itemView.findViewById(personal.study.diary.R.id.image)

        fun bind(contact: Contact) {
            name.text = contact.name
            number.text = contact.phone.toString()
            image.setImageResource(personal.study.diary.R.mipmap.profile_icon)
        }

        companion object {
            fun create(parent: ViewGroup): ContactViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(personal.study.diary.R.layout.item_contact, parent, false)
                return ContactViewHolder(view)
            }
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
