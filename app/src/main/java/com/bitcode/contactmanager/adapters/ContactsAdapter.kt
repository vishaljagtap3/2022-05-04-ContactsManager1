package com.bitcode.contactmanager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcode.contactmanager.R
import com.bitcode.contactmanager.databinding.ContactViewBinding
import com.bitcode.contactmanager.models.Contact

class ContactsAdapter(
  private var contactsList : ArrayList<Contact>
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    class ContactViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
         var binding : ContactViewBinding = ContactViewBinding.bind(itemView)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.contact_view, null)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.binding.contact = contactsList[position]
    }
}